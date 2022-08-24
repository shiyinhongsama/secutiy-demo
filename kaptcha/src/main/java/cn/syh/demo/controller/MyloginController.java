package cn.syh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyloginController {

    @GetMapping("/login.html")
    public String mylogin(){
        return "login";
    }

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }

}
