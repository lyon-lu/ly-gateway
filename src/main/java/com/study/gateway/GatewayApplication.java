package com.study.gateway;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.study.gateway.config.CustomConfig;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayApplication {
	
	@Resource
	private CustomConfig customConfig;
	
	@Bean(name = "redisRateLimiter")
	public RedisRateLimiter redisRateLimiter() {
		
		return new RedisRateLimiter(customConfig.getReplenishRate(),customConfig.getBurstCapacity());
	}
	
	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
                .route("modify_request_body", r -> r.path("/121221/**")
                        .filters(f -> f.modifyRequestBody(String.class, String.class, (exchange,s) -> {
                            System.out.println(s);
                            return Mono.just("wewewwee");
                        }))
                        .uri("http://localhost:8082"))
                .build();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
