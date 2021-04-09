package com.yexiao.demo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import com.yexiao.demo.extra.ding.DingService;
import com.yexiao.demo.extra.weixin.subscribe.MaterialType;
import com.yexiao.demo.extra.weixin.subscribe.PublicNumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

    @Autowired
    DingService dingService;
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() throws IOException {

    }


}
