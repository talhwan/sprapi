package com.thc.sprapi.controller.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TbgsquidWsHandler extends TextWebSocketHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int list_size_limit_ws = 1000;

    private static List<WebSocketSession> list = new ArrayList<>();
    private static Map<String, String> map_session = new HashMap<>();
    
    public void send(Map<String, Object> map_message) throws Exception {
    	logger.info("* map_message : " + map_message);
    	Gson gson = new Gson();							
    	TextMessage message = new TextMessage(gson.toJson(map_message));	
        
    	for(WebSocketSession sess: list) {
        	sess.sendMessage(message);
        }
    }
    public void sendByTbgsquid(Map<String, Object> map_message) throws Exception {
    	logger.info("* map_message : " + map_message);
    	String tbgsquidId = map_message.get("tbgsquidId") + "";
    	Gson gson = new Gson();							
		TextMessage message = new TextMessage(gson.toJson(map_message));
    	
    	for(WebSocketSession sess: list) {
        	String t_tbgsquidId = map_session.get(sess.getId() + "");
        	if(tbgsquidId.equals(t_tbgsquidId)) {
        		sess.sendMessage(message);
        	}
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        logger.info("* payload : " + payload);
        
        for(WebSocketSession sess: list) {
        	sess.sendMessage(message);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	logger.info("1. 접속 : " + session);
    	if(list.size() < list_size_limit_ws) {
    		
    		String session_id = session.getId() + "";
        	String tbgsquidId = session.getUri() + "";
        	
        	String t_param = "?tbgsquidId=";
        	int int_tbgsquidId = tbgsquidId.indexOf(t_param);
        	tbgsquidId = tbgsquidId.substring(int_tbgsquidId);
        	tbgsquidId = tbgsquidId.replace(t_param, "");
        	logger.info("tbgsquidId : " + tbgsquidId);

        	map_session.put(session_id, tbgsquidId);
    		list.add(session);

			sendListSize(tbgsquidId);

    	} else {
    		Map<String, Object> map_message = new HashMap<>();
    		map_message.put("ws_type", "rejected");
    		Gson gson = new Gson();							
        	TextMessage message = new TextMessage(gson.toJson(map_message));	
        	session.sendMessage(message);
    	}
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String session_id = session.getId() + "";
		String t_tbgsquidId = map_session.get(session_id + "");
    	map_session.remove(session_id);
    	
    	logger.info("2. 해제 : " + session);
        list.remove(session);

		sendListSize(t_tbgsquidId);
    }

	public int getListSize(String tbgsquidId) throws Exception {
		int listSize = 0;
		for(WebSocketSession sess: list) {
			String t_tbgsquidId = map_session.get(sess.getId() + "");
			if(tbgsquidId.equals(t_tbgsquidId)) {
				listSize++;
			}
		}
		return listSize;
	}
	public void sendListSize(String tbgsquidId) throws Exception {
		Map<String, Object> map_message = new HashMap<>();
		map_message.put("ws_type", "listSize");
		map_message.put("tbgsquidId", tbgsquidId);
		map_message.put("listSize", getListSize(tbgsquidId));
		sendByTbgsquid(map_message);
	}
}