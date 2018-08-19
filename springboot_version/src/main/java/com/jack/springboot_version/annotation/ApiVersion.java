package com.jack.springboot_version.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * create by jack 2018/8/19
 *
 * @auther jack
 * @date: 2018/8/19 10:44
 * @Description:
 *版本控制注解
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    /**
     * 标识版本号
     * @return
     */
    int value();
}
