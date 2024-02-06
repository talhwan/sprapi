package com.thc.sprapi.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbgrantuser")
@Controller
public class TbgrantuserPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbgrantuser/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbgrantuser/detail";
    }
    @GetMapping("/admin_detail/{id}")
    public String adminDetail(@PathVariable("id") String id){
        return "/tbgrantuser/admin_detail";
    }
    @GetMapping("/admin_list/{id}")
    public String adminList(@PathVariable("id") String id){
        return "/tbgrantuser/admin_list";
    }
    @GetMapping("/admin_create/{id}")
    public String adminInsert(@PathVariable("id") String id){
        return "/tbgrantuser/admin_create";
    }

}
