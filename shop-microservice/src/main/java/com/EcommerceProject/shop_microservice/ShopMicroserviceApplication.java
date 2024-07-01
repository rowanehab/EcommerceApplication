package com.EcommerceProject.shop_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableFeignClients
public class ShopMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopMicroserviceApplication.class, args);
		System.out.println("Hey Shop microservice! from the console");
	}

}
