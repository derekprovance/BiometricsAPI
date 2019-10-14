package com.derekprovance.biometrics.biometricsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BiometricsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BiometricsApiApplication.class, args);
	}
}
