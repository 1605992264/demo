package com.yexiao.demo;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.yexiao.demo.base.extra.alipay.MyAlipayUtils;
import com.yexiao.demo.base.extra.weixin.PublicNumberUtils;
import com.yexiao.demo.base.utils.annotation.MyAspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

    @Autowired
    private PublicNumberUtils publicNumberUtils;

    @Autowired
    private MyAlipayUtils alipayUtils;

    @Test
    public void contextLoads() {
        File file = alipayUtils.generatePayQRCode(IdUtil.fastSimpleUUID(),"0.01","ces1","5m");
    }

}
