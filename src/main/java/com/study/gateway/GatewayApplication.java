package com.study.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;

import com.study.gateway.filter.RemoteAddrKeyResolver;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
	public RemoteAddrKeyResolver remoteAddrKeyResolver() {
		
		return new RemoteAddrKeyResolver();
	}
	
	@Bean(name = "redisRateLimiter")
	public RedisRateLimiter redisRateLimiter() {
		
		return new RedisRateLimiter(1000,2000);
	}
	
	/*@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		 
		return builder.routes()
			        .route(r -> r.path("/gateway")
			          .filters(f -> f.filter(new SecurityFilter())
			        		  .preserveHostHeader()
			        		  .modifyResponseBody(String.class, String.class, (exchange, s) -> {
                                    System.out.println("modifyResponseBody----" + s);
			        			  return s;
                                }))
			          .uri("http://172.18.188.160:8081"))
			        .build();
    }*/
}
