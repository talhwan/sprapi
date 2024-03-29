package com.thc.sprapi.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbnotice")
@Controller
public class TbnoticePageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbnotice/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbnotice/detail";
    }
    @GetMapping("/admin_detail/{id}")
    public String adminDetail(@PathVariable("id") String id){
        return "/tbnotice/admin_detail";
    }

}
