package com.jack.consul4.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create by jack 2019/9/3
 *
 * @author jack
 * @date: 2019/9/3 21:43
 * @Description:
 */
@FeignClient("consul5")
public interface HelloService {
    @RequestMapping(value = "/consul5/test",produces ="application/json" )
    String hello(@RequestParam("name") String name);
}
