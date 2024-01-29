package com.thc.sprapi.controller.page;

import com.thc.sprapi.dto.TbboardDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbboard")
@Controller
public class TbboardPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbboard/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbboard/detail";
    }

}
