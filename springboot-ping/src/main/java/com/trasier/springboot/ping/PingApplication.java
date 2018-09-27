package com.trasier.springboot.ping;

import com.trasier.client.impl.spring.opentracing.boot.EnableTrasierOpentracing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTrasierOpentracing
public class PingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingApplication.class, args);
	}
}
