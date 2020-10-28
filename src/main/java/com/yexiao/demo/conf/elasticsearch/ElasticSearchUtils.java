package com.yexiao.demo.conf.elasticsearch;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexiao.demo.base.domain.BaseEntity;
import com.yexiao.demo.base.domain.BasePage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * @author xuhf
 * @date 2020/10/28 10:41
 **/
@Component
public class ElasticSearchUtils {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建 ES 索引
     *
     * @param index      索引
     * @param properties 文档属性集合
     * @return 返回 true，表示创建成功
     * @throws IOException
     */
    public boolean createIndex(String index, Map<String, Map<String, Object>> properties) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        // ES 7.0 后的版本中，已经弃用 type
        builder.startObject()
                .startObject("mappings")
                .field("properties", properties)
                .endObject()
                .startObject("settings")
                .endObject()
                .endObject();

        CreateIndexRequest request = new CreateIndexRequest(index).source(builder);
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        return response.isAcknowledged();
    }

    /**
     * 判断索引是否存在
     *
     * @param index 索引
     * @return 返回 true，表示存在
     */
    public boolean isExistIndex(String index) throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        getIndexRequest.local(false);
        getIndexRequest.humanReadable(true);
        getIndexRequest.includeDefaults(false);

        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引


     * @param index 索引
     * @return 返回 true，表示删除成功
     */
    public boolean deleteIndex(String index) throws IOException {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(index);
            AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

            return response.isAcknowledged();
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.NOT_FOUND) {
                throw new RuntimeException("Not found index: " + index);
            }
            throw exception;
        }
    }

    /**
     * 保存文档
     * <p>
     * 如果文档存在，则更新文档；如果文档不存在，则保存文档。
     *
     * @param t 文档数据
     */
    public <T extends BaseEntity > void save(String index,T t) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.id(t.getId());
        indexRequest.source(JSON.toJSONString(t), XContentType.JSON);
        // 保存文档数据
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量保存文档
     * <p>
     * 如果集合中有些文档已经存在，则更新文档；不存在，则保存文档。
     *
     * @param index        索引
     * @param documentList 文档集合
     */
    public void saveAll(String index, List<? extends BaseEntity> documentList) throws IOException {
        if (CollectionUtils.isEmpty(documentList)) {
            return;
        }
        // 批量请求
        BulkRequest bulkRequest = new BulkRequest();
        documentList.forEach(doc -> {
            bulkRequest.add(new IndexRequest(index)
                    .id(doc.getId())
                    .source(JSON.toJSONString(doc), XContentType.JSON));
        });

        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

    }

    /**
     * 根据文档 ID 删除文档
     *
     * @param index 索引
     * @param id    文档 ID
     */
    public void delete(String index, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);

        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * 根据查询条件删除文档
     *
     * @param index        索引
     * @param queryBuilder 查询条件构建器
     */
    public void deleteByQuery(String index, QueryBuilder queryBuilder) throws IOException {
        DeleteByQueryRequest deleteRequest = new DeleteByQueryRequest(index).setQuery(queryBuilder);
        deleteRequest.setConflicts("proceed");

        restHighLevelClient.deleteByQuery(deleteRequest, RequestOptions.DEFAULT);

    }

    /**
     * 根据文档 ID 批量删除文档
     *
     * @param index  索引
     * @param idList 文档 ID 集合
     */
    public void deleteAll(String index, List<String> idList) throws IOException {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        BulkRequest bulkRequest = new BulkRequest();
        idList.forEach(id -> bulkRequest.add(new DeleteRequest(index, id)));

        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 根据索引和文档 ID 获取数据
     *
     * @param index 索引
     * @param id    文档 ID
     * @param <T>   数据类型
     * @return T    返回 T 类型的数据
     */
    public <T> T get(String index, String id, Class<T> resultType) throws IOException {
        GetRequest getRequest = new GetRequest(index, id);
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        String resultAsString = response.getSourceAsString();
        return JSON.parseObject(resultAsString, resultType);
    }

    /**
     * 条件查询
     *
     * @param index         索引
     * @param sourceBuilder 条件查询构建起
     * @param <T>           数据类型
     * @return T 类型的集合
     */
    public <T> List<T> searchByQuery(String index, SearchSourceBuilder sourceBuilder, Class<T> resultType) throws IOException {
        // 构建查询请求
        SearchRequest searchRequest = new SearchRequest(index);
        if(sourceBuilder != null) {
            searchRequest.source(sourceBuilder);
        }
        // 获取返回值
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        // 创建空的查询结果集合
        List<T> results = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            // 以字符串的形式获取数据源
            String sourceAsString = hit.getSourceAsString();
            results.add(JSON.parseObject(sourceAsString, resultType));
        }
        return results;
    }

    /**
     * 创建查询
     * */
    public SearchSourceBuilder createQuery(Page page, JSONObject jsonObject){
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 设置查询页数和一页多少个
        builder.from((int) page.getCurrent()-1);
        builder.size((int) page.getSize());
        if(jsonObject.size() != 0 ){
            // 查询类的初始化 用于拼接
            BoolQueryBuilder boolQueryBuilder = boolQuery();
            for(String key : jsonObject.keySet()){
                if(StrUtil.isNotEmpty(jsonObject.getString(key))){
                    boolQueryBuilder.must(matchQuery(key,jsonObject.getString(key)));
                }
            }
            builder.query(boolQueryBuilder);
        }
        // 必须要createDate字段 不然报错
        builder.sort("createDate", SortOrder.DESC);
        return builder;
    }

}
