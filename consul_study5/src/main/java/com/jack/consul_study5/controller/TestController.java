package com.jack.consul_study5.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by jack 2019/9/3
 *
 * @author jack
 * @date: 2019/9/3 21:41
 * @Description:
 */
@RestController
@RequestMapping("consul5")
public class TestController {
    @RequestMapping("test")
    public String test(String name) {
        return "consul5 test,"+name;
    }
}
