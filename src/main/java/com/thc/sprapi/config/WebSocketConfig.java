package com.thc.sprapi.config;

import com.thc.sprapi.controller.socket.TbgsquidWsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final TbgsquidWsHandler tbgsquidWsHandler = new TbgsquidWsHandler();

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(tbgsquidWsHandler, "tbgsquidws").setAllowedOrigins("*");
	}

}