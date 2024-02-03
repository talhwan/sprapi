package com.thc.sprapi.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbpost")
@Controller
public class TbpostPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbpost/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbpost/detail";
    }
    @GetMapping("/admin_detail/{id}")
    public String adminDetail(@PathVariable("id") String id){
        return "/tbpost/admin_detail";
    }

}
