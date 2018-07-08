package com.jack.consul_study2.api;

import com.jack.api.Hello;
import org.springframework.stereotype.Service;

/**
 * create by jack 2018/7/8
 */
@Service
public class ChineseHelloImpl implements Hello {
    @Override
    public String sayHello(String name) {
        return "我是中国人，我说汉语，我的名字是："+name;
    }
}
