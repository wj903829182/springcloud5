package com.jack.consul_study1.controller;

import com.jack.consul_study1.api.Chinese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by jack 2018/7/8
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private Chinese chinese;

    @RequestMapping("/hello")
    public String testHello(String name){
        return chinese.sayHello(name);
    }

}
