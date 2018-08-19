package com.jack.springboot_version.controller.v1;

import com.jack.springboot_version.annotation.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by jack 2018/8/19
 *
 * @auther jack
 * @date: 2018/8/19 10:52
 * @Description:
 */
@ApiVersion(1)
@RestController
@RequestMapping("{version}/hello")
public class Hello1Controller {

    @RequestMapping("/world")
    public String helloWorld(){
        System.out.println("版本是1的接口");
        return "hello,world .version is 1";
    }

}
