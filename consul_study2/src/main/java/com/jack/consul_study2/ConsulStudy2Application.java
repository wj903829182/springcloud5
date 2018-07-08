package com.jack.consul_study2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
public class ConsulStudy2Application {

	public static void main(String[] args) {
		SpringApplication.run(ConsulStudy2Application.class, args);
	}
}
