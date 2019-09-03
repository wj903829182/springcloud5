package com.jack.consul4.controller;

import com.alibaba.fastjson.JSONObject;
import com.jack.consul4.config.StudentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by jack 2019/9/3
 *
 * @author jack
 * @date: 2019/9/3 20:17
 * @Description:
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private StudentConfig studentConfig;
    @RequestMapping("test1")
    public String test1(){
        return JSONObject.toJSONString(studentConfig);
    }
}
