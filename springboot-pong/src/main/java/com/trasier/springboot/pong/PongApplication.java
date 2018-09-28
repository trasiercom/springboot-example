package com.trasier.springboot.pong;

import com.trasier.client.impl.spring.opentracing.boot.EnableTrasierOpentracing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTrasierOpentracing
public class PongApplication {

	public static void main(String[] args) {
		SpringApplication.run(PongApplication.class, args);
	}
}
