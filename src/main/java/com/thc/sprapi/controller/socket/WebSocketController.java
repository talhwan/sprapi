package com.thc.sprapi.controller.socket;

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
import java.util.Map;

@Controller
public class WebSocketController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @MessageMapping("/send")
    @SendTo("/topic/listener")
    public String send(String message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        return message;
    }


}
