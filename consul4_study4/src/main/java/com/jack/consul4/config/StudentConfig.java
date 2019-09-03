package com.jack.consul4.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * create by jack 2019/9/3
 *
 * @author jack
 * @date: 2019/9/3 20:15
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "student")
@Data
public class StudentConfig {
    private String name;
    private Integer age;
}
