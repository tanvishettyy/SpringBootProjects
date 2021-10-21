package org.impelsys.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class DiscoveryServiceApplication {

	public static void main(String[] args) {
		log.info("Starting Discovery Server");
		System.out.println("In discovery server");
		SpringApplication.run(DiscoveryServiceApplication.class, args);
	}

}
