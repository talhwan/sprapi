package com.thc.sprapi.controller.socket;

import com.thc.sprapi.dto.TbuserDto;
import com.thc.sprapi.service.AuthService;
import com.thc.sprapi.service.TbuserService;
import com.thc.sprapi.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebSocketController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;
    private final TbuserService tbuserService;
    public WebSocketController(AuthService authService, TbuserService tbuserService) {
        this.authService = authService;
        this.tbuserService = tbuserService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/listener")
    public Map<String, Object> send(Map<String, Object> param) throws Exception {
        //Thread.sleep(1000); // simulated delay
        String accessToken = param.get("accessToken") + "";
        String tbuserId = authService.verifyAccessToken(accessToken);
        TbuserDto.TbuserSelectDto a = tbuserService.detail(tbuserId);

        Map<String, Object> msg = new HashMap<>();
        msg.put("sender_nick", a.getNick());
        msg.put("message", param.get("message") + "");
        return msg;
    }


}
