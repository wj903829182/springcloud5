package com.jack.hello_starter_test;

import com.jack.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloStarterTestApplication {

	@Autowired
	HelloService helloService;

	public static void main(String[] args) {
		SpringApplication.run(HelloStarterTestApplication.class, args);
	}

	@RequestMapping("/")
	public String index(){
		return helloService.sayHello();
	}

}
