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
        /*System.out.println("CustomHandlerException run -----------------");
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        DataBuffer wrap = response.bufferFactory().wrap("12345".getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(wrap));*/
        return Mono.error(ex);
    }
}
