package com.study.gateway;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;

import com.study.gateway.config.CustomConfig;
import com.study.gateway.filter.RemoteAddrKeyResolver;

@SpringBootApplication
public class GatewayApplication {
	
	@Resource
	private CustomConfig customConfig;
	
	@Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
	public RemoteAddrKeyResolver remoteAddrKeyResolver() {
		
		return new RemoteAddrKeyResolver();
	}
	
	@Bean(name = "redisRateLimiter")
	public RedisRateLimiter redisRateLimiter() {
		
		return new RedisRateLimiter(customConfig.getReplenishRate(),customConfig.getBurstCapacity());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
