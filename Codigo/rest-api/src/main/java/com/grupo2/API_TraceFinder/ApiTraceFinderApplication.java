package com.grupo2.API_TraceFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@SpringBootApplication(scanBasePackages = "br.com.grupo2.API_TraceFinder")
//@EntityScan(basePackages = "br.com.grupo2.API_TraceFinder.classes")
//@EnableJpaRepositories (basePackages = "br.com.grupo2.API_TraceFinder.classes")
public class ApiTraceFinderApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiTraceFinderApplication.class, args);
		//System.out.print(new BCryptPasswordEncoder().encode("123"));
	}
}
