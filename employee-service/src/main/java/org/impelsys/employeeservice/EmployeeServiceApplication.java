package org.impelsys.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication //= configuration + componentscan + enableautoconfiguration
@EnableEurekaClient
public class EmployeeServiceApplication {

	@Bean
	@LoadBalanced   //client side load balancer
	public RestTemplate restTemplate()  //
	{
		return new RestTemplate();   //allows to communicate betwn microservice
	}
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
