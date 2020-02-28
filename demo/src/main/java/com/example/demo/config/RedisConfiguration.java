package com.example.demo.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.ShallowEtagHeaderFilter;


public class RedisConfiguration {
	@Bean
	  JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setPort(6379);
		connectionFactory.setHostName("myconnection");
	    return new JedisConnectionFactory();
	  }

	  @Bean
	  RedisTemplate<String, String> redisTemplate(){
		RedisTemplate<String, String> template = new RedisTemplate<String, String>();
		template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	  }
	  
}
