package com.jack.consul4;

import com.jack.consul4.config.StudentConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class Consul4Application implements CommandLineRunner {

	@Value("${name}")
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(Consul4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Consul4Application.....启动成功"+name);
	}
}
