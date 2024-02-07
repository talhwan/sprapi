package com.thc.sprapi.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbgsquiduser")
@Controller
public class TbgsquiduserPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbgsquiduser/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbgsquiduser/detail";
    }
    @GetMapping("/admin_detail/{id}")
    public String adminDetail(@PathVariable("id") String id){
        return "/tbgsquiduser/admin_detail";
    }

}
