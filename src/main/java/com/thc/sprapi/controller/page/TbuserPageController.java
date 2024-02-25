package com.thc.sprapi.controller.page;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.thc.sprapi.dto.TbuserDto;
import com.thc.sprapi.security.JwtTokenDto;
import com.thc.sprapi.service.TbuserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

@RequestMapping("/tbuser")
@Controller
public class TbuserPageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${google.login.client_id}")
    private String google_client_id;
    private final TbuserService tbuserService;
    public TbuserPageController(TbuserService tbuserService) {
        this.tbuserService = tbuserService;
    }

    @GetMapping("/okcert/result")
    public String okcert() throws Exception {
        logger.info("okcert");
        return "/tbuser/okcert";
    }
    @GetMapping("/login/naver")
    public String naverLogin(HttpServletRequest request) throws Exception {
        return "/tbuser/naver";
    }
    @PostMapping("/login/google")
    public String googleLogin(@RequestParam String credential, Model model){
        logger.info("googleLogin");

        HttpTransport transport = null;
        transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(google_client_id))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        GoogleIdToken idToken = null;
        try{
            idToken = verifier.verify(credential);
        } catch(Exception e){
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            // Print user identifier
            String userId = payload.getSubject();
            logger.info("payload ID: " + payload);
            String username = payload.get("email") + "";
            // TO-DO Password 를 좀더 보안 신경써보자!!
            String password = payload.get("sub") + "";
            JwtTokenDto jwtTokenDto = tbuserService.sns(new TbuserDto.TbuserCreateDto(payload.get("email") + "", payload.get("sub") + "","","google","0"));
            model.addAttribute("token", jwtTokenDto);
        } else {
            System.out.println("Invalid ID token.");
        }
        return "/tbuser/google";
    }

    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbuser/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbuser/detail";
    }
    @GetMapping("/admin_detail/{id}")
    public String adminDetail(@PathVariable("id") String id){
        return "/tbuser/admin_detail";
    }

}
