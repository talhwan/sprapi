package com.thc.sprapi.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbcrewuser")
@Controller
public class TbcrewuserPageController {

    @GetMapping("/admin_list/{pid}")
    public String adminList(@PathVariable("pid") String pid){
        return "/tbcrewuser/admin_list";
    }
    @GetMapping("/admin_create/{pid}")
    public String adminCreate(@PathVariable("pid") String pid){
        return "/tbcrewuser/admin_create";
    }
    /**/
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbcrewuser/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbcrewuser/detail";
    }
    @GetMapping("/admin_detail/{id}")
    public String adminDetail(@PathVariable("id") String id){
        return "/tbcrewuser/admin_detail";
    }


}
