package com.yexiao.demo.utils;

import cn.hutool.crypto.SecureUtil;
import com.yexiao.demo.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author xuhf
 * @date 2020/8/26 16:41
 **/
public class UserUtils {

    /**
     * shiro获取当前用户登入的信息
     * */
    public static UserDO getUser(){
        if(SecurityUtils.getSecurityManager() != null) {
            Object principal = SecurityUtils.getSubject().getPrincipal();
            if (principal != null && principal instanceof UserDO) {
                ((UserDO) principal).setToken(SecurityUtils.getSubject().getSession().getId().toString());
            }
            return (UserDO) principal;
        }
        return null;
    }

    /**
     * spring-security 获取当前用户登入的信息
     * */
//    public static UserDO getUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication != null && authentication.getPrincipal() instanceof UserDO){
//            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
//            UserDO userDO = ((UserDO) authentication.getPrincipal());
//            if(userDO != null){
//                //userDO.setToken(((WebAuthenticationDetails)details).getSessionId());
//            }
//            return userDO;
//        }
//        return null;
//    }

    /**
     * @param password 密码
     * @return 返回加密的密码
     * */
    public static String newPassword(String password){
        String s = SecureUtil.md5(password);
        return s;
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
