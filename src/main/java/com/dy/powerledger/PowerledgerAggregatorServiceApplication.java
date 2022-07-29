package com.dy.powerledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class PowerledgerAggregatorServiceApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PowerledgerAggregatorServiceApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PowerledgerAggregatorServiceApplication.class);
    }
}
