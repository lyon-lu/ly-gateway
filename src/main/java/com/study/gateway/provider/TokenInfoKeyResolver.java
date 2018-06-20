/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TokenInfoKeyResolver.java created on Jun 6, 2018 10:10:09 AM by Lyon Lu 
 */
package com.study.gateway.provider;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 6, 2018 10:10:09 AM
 *
 * </pre>
 */
@Component
public class TokenInfoKeyResolver implements KeyResolver
{
    public static final String BEAN_NAME = "tokenInfoKeyResolver";

    @Override
    public Mono<String> resolve(ServerWebExchange exchange)
    {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst("accessToken");
        String channel = headers.getFirst("channelCode");
        return Mono.just(channel + token);
    }

}
