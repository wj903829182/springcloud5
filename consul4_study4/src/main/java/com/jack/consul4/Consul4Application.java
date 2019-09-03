package com.jack.consul4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 参考：https://blog.csdn.net/yp090416/article/details/81587110
 * http://www.imooc.com/article/286883?block_id=tuijian_wz
 */
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
