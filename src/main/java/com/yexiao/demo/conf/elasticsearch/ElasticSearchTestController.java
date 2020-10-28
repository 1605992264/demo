package com.yexiao.demo.conf.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.base.domain.BasePage;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.domain.LogDO;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/10/28 11:37
 **/
@RestController
@RequestMapping("elastic")
public class ElasticSearchTestController {

    @Autowired
    private ElasticSearchUtils elasticSearchUtils;

    @RequestMapping("/log/save")
    public R save(LogDO logDO){
        logDO.setCreateDate(System.currentTimeMillis());
        try {
            elasticSearchUtils.save("log",logDO);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.success();
    }

    @RequestMapping("/log/form")
    public R list(String id){
        LogDO log = null;
        try {
            log = elasticSearchUtils.get("log", id, LogDO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("row",log);
        return R.success(map);
    }

    @RequestMapping("/log/list")
    public R list(BasePage<LogDO> page, LogDO logDO){
        SearchSourceBuilder query = elasticSearchUtils.createQuery(page.newMybatisPlusPage(), JSONObject.parseObject(JSONObject.toJSONString(logDO)));
        List<LogDO> log = null;
        try {
            log = elasticSearchUtils.searchByQuery("log",query , LogDO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(log);
    }

}
