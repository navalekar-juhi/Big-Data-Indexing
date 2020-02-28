package com.example.demo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
public class Info7255demoApplication {
	
	@Bean
	  public Filter shallowEtagHeaderFilter() {
	   return new ShallowEtagHeaderFilter();
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(Info7255demoApplication.class, args);
	}

}
