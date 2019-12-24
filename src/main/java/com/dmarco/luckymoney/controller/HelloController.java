package com.dmarco.luckymoney.controller;

import com.dmarco.luckymoney.config.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private LimitConfig limitConfig;

    @GetMapping("/say")
    public String say(){
        return "说明："+limitConfig.getDescription();
    }
    @PostMapping("/post")
    public String post(){
        return "说明："+limitConfig.getDescription();
    }
}
