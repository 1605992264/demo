package com.yexiao.demo.base.extra.alipay;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author xuhf
 * @date 2020/8/17 17:47
 **/
@Component
public class MyAlipayUtils {

    @Value("${param.alipay.url}")
    private String url;

    @Value("${param.alipay.appId}")
    private String appId;

    @Value("${param.alipay.privateKey}")
    private String privateKey;

    @Value("${param.alipay.publicKey}")
    private String publicKey;

    @Value("${param.alipay.QRPath}")
    private String QRPath;

    private AlipayClient alipayClient = null;

    public AlipayClient getAlipayCilent(){
            if (alipayClient == null) {
                alipayClient = new
                        DefaultAlipayClient(url,
                        appId,
                        privateKey,
                        "json",
                        "utf-8",
                        publicKey,
                        "RSA2");
            }
        return alipayClient;
    }

    /**
     * 生成支付订单链接
     * @param number 订单号
     * @param money 金钱
     * @param title 标题
     * @param timeOut 二维表生效时间
     * @return 订单链接
     * */
    public String generatePayUrl(String number,String money,String title,String timeOut){
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", number);
        jsonObject.put("total_amount", money);
        jsonObject.put("subject", title);
        jsonObject.put("qr_code_timeout_express", timeOut);
        request.setBizContent(jsonObject.toJSONString());
        AlipayTradePrecreateResponse response = null;
        try {
            response = getAlipayCilent().execute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException("生成二维码链接失败" + e.getErrMsg());
        }
        return response.getQrCode();
    }


    /**
     * 生成二维码支付订单
     * @param number 订单号
     * @param money 金钱
     * @param title 标题
     * @param timeOut 二维表生效时间
     * @return 生成的二维码
     * */
    public File generatePayQRCode(String number,String money,String title,String timeOut) {
        String url = generatePayUrl(number,money,title,timeOut);
        File QRcode = QrCodeUtil.generate(url, QrConfig.create()
                        .setWidth(300)
                        .setHeight(300),
                    FileUtil.file(QRPath));
        System.out.println("二维码生成成功");
        return QRcode;
    }

    /**
     * 查询支付订单信息
     * @param number 订单号
     * @return 返回订单信息
     * */
    public AlipayTradeQueryResponse selectPay(String number){
        AlipayTradeQueryRequest selectRequest = new AlipayTradeQueryRequest();
        JSONObject selectJsonObject = new JSONObject();
        selectJsonObject.put("out_trade_no",number);
        selectRequest.setBizContent(selectJsonObject.toJSONString());
        try {
            AlipayTradeQueryResponse response = getAlipayCilent().execute(selectRequest);
            return response;
        } catch (AlipayApiException e) {
            throw new RuntimeException("查询失败" + e.getErrMsg());
        }
    }

    /**
     * 商家扫描付钱码
     * @param number 订单号
     * @param authCode 客户付钱码
     * @param subject 标题
     * @param money 金钱
     * */
    public AlipayTradePayResponse barCodePay(String number,String authCode,String subject,String money){
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no", number);
        jsonObject.put("scene","bar_code");
        jsonObject.put("auth_code",authCode);
        jsonObject.put("subject",subject);
        jsonObject.put("total_amount",money);
        jsonObject.put("timeout_express","5m");
        request.setBizContent(jsonObject.toJSONString());
        try {
            AlipayTradePayResponse execute = getAlipayCilent().execute(request);
            return execute;
        } catch (AlipayApiException e) {
            throw new RuntimeException("扫描付钱码失败" + e.getErrMsg());
        }
    }

    /**
     * 退款
     * @param number 订单号
     * @return 返回退款信息
     * */
    public String cancelPay(String number){
        AlipayTradeCancelRequest cancelRequest = new AlipayTradeCancelRequest();
        JSONObject cancelJson = new JSONObject();
        cancelJson.put("out_trade_no",number);
        cancelRequest.setBizContent(cancelJson.toJSONString());
        AlipayTradeCancelResponse cancelExecute = null;
        try {
            cancelExecute = getAlipayCilent().execute(cancelRequest);
        } catch (AlipayApiException e) {
            throw new RuntimeException("退款失败" + e.getErrMsg());
        }
        if("refund".equals(cancelExecute.getAction())){
            return "退款成功";
        }
        return "退款失败";
    }



}
