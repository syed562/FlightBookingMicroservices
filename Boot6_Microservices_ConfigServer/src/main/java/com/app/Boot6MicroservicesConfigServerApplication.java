package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Boot6MicroservicesConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot6MicroservicesConfigServerApplication.class, args);
	}

}
