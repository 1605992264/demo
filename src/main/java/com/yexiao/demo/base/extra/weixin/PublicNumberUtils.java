package com.yexiao.demo.base.extra.weixin;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhf
 * @date 2020/8/24 11:28
 * 微信公众号
 **/
@Component
public class PublicNumberUtils {

    @Value("${param.weiXin.publicNumber.appId}")
    private String appId;

    @Value("${param.weiXin.publicNumber.appSecret}")
    private String appSecret;

    @Value("${param.weiXin.publicNumber.getTokenUrl}")
    private String tokenUrl;

    @Value("${param.weiXin.publicNumber.getUploadUrl}")
    private String uploadUrl;

    @Value("${param.weiXin.publicNumber.getImgAndTextUrl}")
    private String imgAndTextUrl;

    @Value("${param.weiXin.publicNumber.getInterimUploadUrl}")
    private String interimUploadUrl;

    @Value("${param.weiXin.publicNumber.getInterimImgAndTextUrl}")
    private String interimImgAndTextUrl;

    @Value("${param.weiXin.publicNumber.getSendGroupMessageUrl}")
    private String sendGroupMessageUrl;

    @Value("${param.weiXin.publicNumber.getUserOpenIdUrl}")
    private String userOpenIdUrl;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取token
     * */
    public String getToken(){
        redisTemplate.delete("publicNumberToken");
        Object token = redisTemplate.opsForValue().get("publicNumberToken");
        if(token != null && StrUtil.isNotEmpty(token.toString())){
            return token.toString();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("appid",appId);
        map.put("secret",appSecret);
        JSONObject response = JSONObject.parseObject(HttpUtil.get(tokenUrl, map));
        String access_token = response.getString("access_token");
        if(StrUtil.isEmpty(access_token)){
            throw new RuntimeException("获取token失败:"+response);
        }else {
            redisTemplate.opsForValue().set("publicNumberToken",access_token,1, TimeUnit.HOURS);
            return access_token;
        }
    }

    /**
     * 上传临时素材
     * */
    public String upload(File file, MaterialType type){
        Map<String,Object> map = new HashMap<>(3);
        map.put("type",type);
        map.put("media",file);
        map.put("access_token",getToken());
        JSONObject response = JSONObject.parseObject(HttpUtil.post(interimUploadUrl, map));
        String media_id = response.getString("media_id");
        if(StrUtil.isEmpty(media_id)){
            throw new RuntimeException("上传素材失败:" + response);
        }else {
            return media_id;
        }
    }

    /**
     * 上传图文消息素材
     * */
    public String imgAndTextMessage(List<ImgAndTextDO> list){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articles", JSONArray.parseArray(JSONArray.toJSONString(list)));
        String url = MessageFormat.format(interimImgAndTextUrl,getToken());
        JSONObject response = JSONObject.parseObject(HttpUtil.post(url,jsonObject.toJSONString()));
        String media_id = response.getString("media_id");
        if(StrUtil.isEmpty(media_id)){
            throw new RuntimeException("上传图文消息失败:" + response);
        }else {
            return media_id;
        }
    }

    /**
     * 发送图文消息（群发）
     * */
    public String sendGroupMessage(String mediaId){
        JSONObject jsonObject = new JSONObject();
        JSONObject filter = new JSONObject();
        filter.put("is_to_all",true);
        filter.put("tag_id",2);
        jsonObject.put("filter",filter);
        JSONObject mpnews = new JSONObject();
        mpnews.put("media_id",mediaId);
        jsonObject.put("mpnews",mpnews);
        jsonObject.put("msgtype","mpnews");
        jsonObject.put("send_ignore_reprint",0);
        String url = MessageFormat.format(sendGroupMessageUrl,getToken());
        JSONObject response = JSONObject.parseObject(HttpUtil.post(url, jsonObject.toJSONString()));
        String errCode = response.getString("errcode");
        if(StrUtil.isEmpty(errCode) || !"0".equals(errCode)){
            throw new RuntimeException("发送图文消息失败:" + response);
        }else {
            return "success";
        }
    }

    /**
     * 获取订阅的用户openid数组
     * */
    public String[] getUser(){
        Map<String,Object> map = new HashMap<>();
        map.put("access_token",getToken());
        map.put("next_openid","");
        JSONObject jsonObject = JSONObject.parseObject(HttpUtil.get(userOpenIdUrl, map));
        Object openid = jsonObject.get("openid");
        return (String[]) openid;
    }

    /**
     * 获取下一个订阅的用户openid
     * */
    public String getUser(String openId){
        Map<String,Object> map = new HashMap<>();
        map.put("access_token",getToken());
        map.put("next_openid",openId);
        JSONObject jsonObject = JSONObject.parseObject(HttpUtil.get(userOpenIdUrl, map));
        String openid = jsonObject.getString("next_openid");
        return openid;
    }

}
