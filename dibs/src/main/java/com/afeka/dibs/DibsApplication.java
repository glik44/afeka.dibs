package com.afeka.dibs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import stockexchange.client.StockExchangeClient;
import stockexchange.client.StockExchangeClientImplementation;


@SpringBootApplication
@EnableScheduling
public class DibsApplication{

	public static void main(String[] args) {
		SpringApplication.run(DibsApplication.class, args);
	}
	
	@Bean
	@Autowired
	public StockExchangeClient client(){
	    return new StockExchangeClientImplementation();
	}

}
