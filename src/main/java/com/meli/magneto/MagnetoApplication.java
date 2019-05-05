package com.meli.magneto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MagnetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagnetoApplication.class, args);
	}

}
