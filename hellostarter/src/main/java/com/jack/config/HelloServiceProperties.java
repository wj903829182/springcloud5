package com.jack.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * create by jack 2018/12/6
 *
 * @author jack
 * @date: 2018/12/6 21:08
 * @Description:
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {
    private static final String MSG = "world";
    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
