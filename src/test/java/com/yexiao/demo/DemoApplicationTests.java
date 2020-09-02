package com.yexiao.demo;

import com.yexiao.demo.base.extra.email.EmailUtils;
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
    private EmailUtils emailUtils;

    @Test
    public void contextLoads() {

    }

}
