/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * CustomHandlerException.java created on Jun 14, 2018 2:01:03 PM by Lyon Lu 
 */
package com.study.gateway.exception;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 14, 2018 2:01:03 PM
 *
 * </pre>
 */
@Component
public class CustomHandlerException implements ErrorWebExceptionHandler
{
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex)
    {
        System.out.println("error ------------------------------");
        return Mono.error(ex);
    }
}
