package com.yexiao.demo;

import com.yexiao.demo.base.extra.weixin.PublicNumberUtils;
import com.yexiao.demo.base.utils.annotation.MyAspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

    @Autowired
    private PublicNumberUtils publicNumberUtils;

    @Test
    @MyAspect
    public void contextLoads() {
        System.out.println("测试");
    }

}
