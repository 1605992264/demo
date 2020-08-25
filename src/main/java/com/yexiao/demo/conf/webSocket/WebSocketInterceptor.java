package com.yexiao.demo.conf.webSocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * WebSocket链接时的拦截器
 * @author 01
 */
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
	
	/**
	 * 当客户端与服务器端握手之前之前执行的方法
	 * 取出当前存在session的用户信息将dunId，封装到WebSocket对象中的map中；
	 * 由Handler处理器中获取id
	 * @return
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler,
			Map<String, Object> attribute) throws Exception {
	        return super.beforeHandshake(request,response,handler,attribute);
	}
		
	/**
	 * 与服务器websocket建立握手之后执行的方法
	 */
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception exception) {
		super.afterHandshake(request, response, handler, exception);
	}
 
}
