package com.jack.consul_study2.controller;

import com.jack.api.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by jack 2018/7/8
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private Hello hello;
    @RequestMapping("/say")
    public String sayHello(@RequestBody  String name){
        return hello.sayHello(name);
    }
}
