package com.yexiao.demo.conf.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	/**
	 * 提供配置自己的websocket类即请求路径
	 */
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myHandler(), "/handler").setAllowedOrigins("*")
				.addInterceptors(new WebSocketInterceptor());
	}

	/**
	 * 向spring容器注册javabean由spring容器来管理
	 * @return
	 */
	@Bean
	public WebSocketHandler myHandler() {
		return new MyHandler();
	}

}
