package com.thc.sprapi.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbcrewushot")
@Controller
public class TbcrewushotPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return "/tbcrewushot/" + page;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id){
        return "/tbcrewushot/detail";
    }
    @GetMapping("/admin_detail/{id}")
    public String adminDetail(@PathVariable("id") String id){
        return "/tbcrewushot/admin_detail";
    }

    @GetMapping("/admin_list/{id}")
    public String adminList(@PathVariable("id") String id){
        return "/tbcrewushot/admin_list";
    }

}
