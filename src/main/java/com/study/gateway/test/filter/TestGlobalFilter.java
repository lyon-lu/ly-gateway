/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TestGlobalFilter.java created on Jun 6, 2018 5:37:26 PM by Lyon Lu 
 */
package com.study.gateway.test.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 6, 2018 5:37:26 PM
 *
 * </pre>
 */
@Component
public class TestGlobalFilter implements GlobalFilter, Ordered
{
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        System.out.println("TestGlobalFilter run --------------------");
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder()
    {
        return -100;
    }
}
