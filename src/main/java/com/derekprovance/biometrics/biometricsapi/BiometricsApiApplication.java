package com.derekprovance.biometrics.biometricsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
public class BiometricsApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BiometricsApiApplication.class, args);
	}
}
