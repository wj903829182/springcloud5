package com.jack.webflux_redis.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * create by jack 2018/5/13
 * City 必须实现序列化，因为需要将对象序列化后存储到 Redis。如果没实现 Serializable，
 * 会引出异常：java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable
 * payload but received an object of type。
 如果不是用默认的序列化，需要自定义序列化实现，只要实现 RedisSerializer 接口去实现即可，
 然后在使用 RedisTemplate.setValueSerializer 方法去设置你实现的序列化实现，支持 JSON、XML 等。
 */
@Data
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     * @Id 注解标记对应库表的主键或者唯一标识符。因为这个是我们的 DO，数据访问对象一一映射到数据存储。
     */
    @Id
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;
}
