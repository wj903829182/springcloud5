package com.jack.consul_study1.api;

import com.jack.api.Hello;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by jack 2018/7/8
 */
@FeignClient("consul2")
public interface Chinese extends Hello {
    @RequestMapping(value = "/hello/say")
    @Override
    String sayHello(String name);
}
