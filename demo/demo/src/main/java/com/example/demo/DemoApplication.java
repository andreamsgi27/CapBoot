package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.targetthings.GetCountryResponse;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
  CommandLineRunner lookup(CountryClient countryClient) {
    return args -> {
      String country = "Spain";

      if (args.length > 0) {
        country = args[0];
      }
      GetCountryResponse response = countryClient.getCountry(country);
      System.err.println(response.getCountry().getCurrency());
    };
  }

}
