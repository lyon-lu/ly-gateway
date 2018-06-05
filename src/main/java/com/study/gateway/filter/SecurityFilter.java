package com.study.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.study.gateway.result.WrapResult;

import reactor.core.publisher.Mono;

@Component
public class SecurityFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig>{

	public SecurityFilter() {
		super(NameConfig.class);
	}
	/*@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		HttpHeaders headers = request.getHeaders();
		List<String> token = headers.get("token");
		List<String> channel = headers.get("channel");
		System.out.println("token===" + token + "channel ===" + channel);
		
		ServerHttpResponse response = exchange.getResponse();
		DataBufferFactory bufferFactory = response.bufferFactory();
		WrapResult result = new WrapResult();
		result.setCode("ggg");
		String code = "ggg";
		
		DataBuffer wrap = bufferFactory.wrap(code.getBytes(StandardCharsets.UTF_8));
		//DataBuffer dataBuffer = bufferFactory.allocateBuffer().write("Using exchange!".getBytes(StandardCharsets.UTF_8));
		//response.writeWith(Mono.just(wrap)).log()
		return chain.filter(exchange);
	}*/

	@Override
	public GatewayFilter apply(NameConfig config) {
		 return (exchange, chain) -> {
			 	ServerHttpRequest request = exchange.getRequest();
				HttpHeaders headers = request.getHeaders();
				List<String> token = headers.get("token");
				List<String> channel = headers.get("channel");
				System.out.println("token===" + token + "channel ===" + channel);
				
				ServerHttpResponse response = exchange.getResponse();
				DataBufferFactory bufferFactory = response.bufferFactory();
				WrapResult result = new WrapResult();
				result.setCode("ggg");
				String code = "ggg";
				
				DataBuffer wrap = bufferFactory.wrap(code.getBytes(StandardCharsets.UTF_8));
				//DataBuffer dataBuffer = bufferFactory.allocateBuffer().write("Using exchange!".getBytes(StandardCharsets.UTF_8));
				//response.writeWith(Mono.just(wrap)).log()
				//chain.filter(exchange)
				return response.writeWith(Mono.just(wrap)).log();
	        };
	}
}
