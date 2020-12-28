package com.yexiao.demo.utils;

import com.yexiao.demo.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Date;

/**
 * @author xuhf
 * @date 2020/8/26 16:41
 **/
public class UserUtils {

    /**
     * 获取当前用户登入的信息
     * */
    public static UserDO getUser(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal != null && principal instanceof  UserDO) {
            ((UserDO) principal).setToken(SecurityUtils.getSubject().getSession().getId().toString());
        }
        return (UserDO) principal;
    }

    /**
     * @param password 密码
     * @return 返回token
     * */
    public static String newToken(String password){
        String newToken = new SimpleHash("md5", password,
                new Date().toString(), 3).toHex();
        return newToken;
    }

    /**
     * @param password 密码
     * @return 返回加密的密码
     * */
    public static String newPassword(String password,String salt){
        String newPassword = new SimpleHash("md5", password,
                salt, 3).toHex();
        return newPassword;
    }

    /**
     * 验证登入用户是否正确
     * @param authorization token
     * @param userId id
     * */
    public static boolean verification(String authorization,String userId){
        UserDO user = getUser();
        if(user == null || !user.getToken().equals(authorization)
                || !user.getId().equals(userId)) {
            return false;
        }
        return true;
    }




}
