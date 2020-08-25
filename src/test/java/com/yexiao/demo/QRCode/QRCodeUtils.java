package com.yexiao.demo.QRCode;


import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QRCodeUtils {

    private final String url = "http://www.baidu.com";

    @Test
    public void Test(){
        QrCodeUtil.generate(url, QrConfig.create()
                        .setWidth(300)
                        .setHeight(300)
                        .setForeColor(Color.blue)
                        .setImg("D://a.png"),
                FileUtil.file("d:/qrcode.jpg"));
        System.out.println("生成成功");
    }

}



