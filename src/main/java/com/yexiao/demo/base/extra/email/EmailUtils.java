package com.yexiao.demo.base.extra.email;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author xuhf
 * @date 2020/9/2 11:19
 **/
@Component
public class EmailUtils {

    @Value("${param.email.host}")
    private String host;

    @Value("${param.email.from}")
    private String from;

    @Value("${param.email.username}")
    private String username;

    @Value("${param.email.password}")
    private String password;

    private static MailAccount account = null;

    private MailAccount getAccount(){
        if(account == null) {
            account = new MailAccount();
            account.setHost(host);
            account.setPort(25);
            account.setAuth(true);
            account.setFrom(from);
            account.setUser(username);
            account.setPass(password);
        }
        return account;
    }

    /**
     * 发送邮箱消息
     * */
    public void send(String to, String subject, String content, boolean isHtml, File ...files){
        MailUtil.send(getAccount(),to,subject,content,isHtml,files);
    }

}
