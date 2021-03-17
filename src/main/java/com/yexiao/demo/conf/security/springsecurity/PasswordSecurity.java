package com.yexiao.demo.conf.security.springsecurity;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author xuhf
 * @date 2021/3/16 14:07
 **/
@Component
public class PasswordSecurity implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        String s = SecureUtil.md5(String.valueOf(charSequence));
        return s;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String encode = encode(charSequence);
        return encode.equals(s);
    }


}
