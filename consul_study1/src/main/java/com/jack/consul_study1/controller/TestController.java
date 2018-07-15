package com.jack.consul_study1.controller;

import com.jack.consul_study1.api.Chinese;
import com.jack.consul_study1.config.StudentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${myName}")
    private String myName;

    @Autowired
    private StudentConfig studentConfig;

    @RequestMapping("/hello")
    public String testHello(String name){
        System.out.println("my name is : "+myName);
        return chinese.sayHello(name);
    }

    @RequestMapping("/myname")
    public String testHello(){
        System.out.println("my name is : "+myName);
        return myName;
    }

    @RequestMapping("/config")
    public String testConfig(){
        System.out.println(studentConfig.toString());
        return studentConfig.toString();
    }



}
