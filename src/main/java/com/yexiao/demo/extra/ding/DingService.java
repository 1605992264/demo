package com.yexiao.demo.extra.ding;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiImChatScenegroupGetRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiV2UserListRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiImChatScenegroupGetResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiV2UserListResponse;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhf
 * @date 2021/3/1 15:28
 **/
@Component
public class DingService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private DingProperty dingProperty;

    public DingService(DingProperty dingProperty) {
        this.dingProperty = dingProperty;
    }

    private final String ACCESS_TOKEN = "ACCESS_TOKEN";

    private String getToken(){
        redisTemplate.delete(ACCESS_TOKEN);
        String s = redisTemplate.opsForValue().get(ACCESS_TOKEN);
        if(StrUtil.isEmpty(s)) {
            DingTalkClient dingTalkClient = new DefaultDingTalkClient(dingProperty.getGetTokenURL());
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(dingProperty.getAppKey());
            request.setAppsecret(dingProperty.getAppSecret());
            try {
                OapiGettokenResponse execute = dingTalkClient.execute(request);
                String accessToken = execute.getAccessToken();
                redisTemplate.opsForValue().set(ACCESS_TOKEN, accessToken,90, TimeUnit.MINUTES);
                return accessToken;
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    /**
     * 获取所有用户
     * */
    public List<JSONObject> getAllUser(){
        List<JSONObject> objectList = new ArrayList<>();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/list");
        OapiV2UserListRequest request = new OapiV2UserListRequest();
        request.setCursor(0L);
        request.setSize(100L);
        request.setDeptId(1L);
        try {
            OapiV2UserListResponse execute = client.execute(request,getToken());
            List<OapiV2UserListResponse.ListUserResponse> list = execute.getResult().getList();
            objectList = JSONArray.parseArray(JSONArray.toJSONString(list), JSONObject.class);
            System.out.println(execute);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    /**
     * 发送消息
     * */
    public void sendMsgToUser(){

    }

    /**
     * 发送消息
     * */
    public void sendMsgToAll(){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setAgentId(dingProperty.getAgentId());
        request.setToAllUser(true);
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("text");
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        msg.getText().setContent("test123");
        request.setMsg(msg);
        try {
            OapiMessageCorpconversationAsyncsendV2Response execute = client.execute(request,getToken());
            System.out.println(execute.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    public void test() {


    }
}
