package com.yexiao.demo.conf.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.conf.SpringContextHolder;
import com.yexiao.demo.conf.shiro.RedisSessionDAO;
import com.yexiao.demo.utils.UserUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/26 15:36
 * */
@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //controller方法处理完毕前
        String token = request.getHeader("Authorization");
        String userId = request.getHeader("UserId");
        Boolean flag = UserUtils.verification(token,userId);
        if(!flag) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 999);
            map.put("message", "请重新登录");
            String string = JSONObject.toJSONString(map);
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
            return false;
        }
        RedisSessionDAO redisSessionDAO = SpringContextHolder.getBean("sessionDAO");
        redisSessionDAO.update(redisSessionDAO.readSession(token));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //controller方法处理完毕后，调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //页面渲染完毕后调用此方法
    }

}