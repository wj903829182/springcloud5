package com.jack.consul_study1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by jack 2018/7/8
 */
@RestController
public class HealthController {
    @RequestMapping("/health")
    public String health(){
        return "health";
    }
}
