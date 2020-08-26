package com.yexiao.demo.base.utils;

import com.yexiao.demo.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * @author xuhf
 * @date 2020/8/26 16:41
 **/
public class UserUtils {




    public static UserDO getUser(){
        return (UserDO) SecurityUtils.getSubject().getPrincipal();
    }




}
