/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * ResponseWrapperFilter.java created on Jul 27, 2018 3:07:55 PM by Lyon Lu 
 */
package com.study.gateway.test.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 27, 2018 3:07:55 PM
 *
 * </pre>
 */
@Component
public class TestWebFilter implements WebFilter
{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain)
    {
        System.out.println("webFilter run ------------");
        return chain.filter(exchange);
    }

}
