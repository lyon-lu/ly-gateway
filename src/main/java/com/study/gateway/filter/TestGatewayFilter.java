/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TestGatewayFilter.java created on Jun 6, 2018 5:42:48 PM by Lyon Lu 
 */
package com.study.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 6, 2018 5:42:48 PM
 *
 * </pre>
 */
public class TestGatewayFilter implements GatewayFilter, Ordered
{
    @Override
    public int getOrder()
    {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        return chain.filter(exchange);
    }
    

}
