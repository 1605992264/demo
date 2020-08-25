package com.yexiao.demo.base.extra.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.yexiao.demo.conf.ConfigParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xuhf
 * @date 2020/8/17 14:43
 **/
@Component
public class AlipayCilent {

    @Autowired
    private ConfigParams params;

    private static AlipayClient alipayClient = null;

    public AlipayClient getAlipayCilent(){
        if(alipayClient == null) {
            alipayClient = new
                    DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                    params.getAppId(),
                    params.getPrivateKey(),
                    "json",
                    "utf-8",
                    params.getPublicKey(),
                    "RSA2");
        }
        return alipayClient;
    }







}
