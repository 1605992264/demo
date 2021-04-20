package com.yexiao.demo.conf.security.shiro;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.CharEncoding;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.springframework.messaging.handler.annotation.Header;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

/**
 * @author xuhf
 * @date 2021/4/9 15:00
 **/
public class AuthenticationFailFilter extends AuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String header = httpServletRequest.getHeader("Authorization");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        response.setCharacterEncoding("utf-8");
        httpResponse.setContentType(ContentType.APPLICATION_JSON.toString());
        if( header != null && !header.isEmpty()){
            httpResponse.setStatus(999);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",999);
            jsonObject.put("message","登入信息已过期");
            httpResponse.getWriter().write(jsonObject.toJSONString());
        }else {
            httpResponse.setStatus(998);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",998);
            jsonObject.put("message","请先登入");
            httpResponse.getWriter().write(jsonObject.toJSONString());
        }
        return false;
    }
}
