package com.study.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.study.gateway.config.CustomConfig;
import com.study.gateway.pojo.TokenInfo;
import com.study.gateway.result.WrapResult;

import reactor.core.publisher.Mono;
/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 5, 2018 4:50:16 PM
 *
 * </pre>
 */
@Component
public class TokenVerificationGatewayFilterFactory extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig>{

	@Resource
	private CustomConfig customConfig;
	
	public TokenVerificationGatewayFilterFactory() {
		super(NameConfig.class);
	}

	@Override
	public GatewayFilter apply(NameConfig config) {
		 return (exchange, chain) -> {
			 	ServerHttpRequest request = exchange.getRequest();
				HttpHeaders headers = request.getHeaders();
				List<String> token = headers.get("token");
				List<String> channel = headers.get("channel");
				System.out.println("token===" + token + "channel ===" + channel);
				List<TokenInfo> tokenList = customConfig.getTokenList();
				
				
				
				if("ASDFGH".equalsIgnoreCase(token.get(0)) && "heqiauto".equalsIgnoreCase(channel.get(0)))
				{
					return chain.filter(exchange);
				}
				ServerHttpResponse response = exchange.getResponse();
				DataBufferFactory bufferFactory = response.bufferFactory();
				WrapResult result = new WrapResult();
				result.setCode("ggg");
				String code = "ggg";
				
				DataBuffer wrap = bufferFactory.wrap(code.getBytes(StandardCharsets.UTF_8));
				return response.writeWith(Mono.just(wrap)).log();
	        };
	}
}
