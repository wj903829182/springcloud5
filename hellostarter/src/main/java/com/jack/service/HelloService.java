package com.jack.service;

/**
 * create by jack 2018/12/6
 *
 * @author jack
 * @date: 2018/12/6 21:10
 * @Description:
 */
public class HelloService {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String sayHello(){
        return "Hello " + msg;
    }
}
