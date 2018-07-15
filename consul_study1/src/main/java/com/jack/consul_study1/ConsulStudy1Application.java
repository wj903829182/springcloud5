package com.jack.consul_study1;

import com.jack.consul_study1.config.StudentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties({StudentConfig.class})
public class ConsulStudy1Application {

	public static void main(String[] args) {
		SpringApplication.run(ConsulStudy1Application.class, args);
	}
}
