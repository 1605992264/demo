package com.yexiao.demo.conf.security.shiro;

import cn.hutool.core.util.StrUtil;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author xuhf
 * @date 2020/12/23 10:25
 **/
public class WebSessionManager extends DefaultWebSessionManager {

    public static final String token = "Authorization";

    /**
     * 定义shiro如何获取sessionId
     * */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String sessionId = httpRequest.getHeader(token);
        if(StrUtil.isNotEmpty(sessionId)){
            httpRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,sessionId);
            httpRequest.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,true);
            return sessionId;
        }
        return super.getSessionId(request, response);
    }
}
