/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * CustomHandlerException.java created on Jun 14, 2018 2:01:03 PM by Lyon Lu 
 */
package com.study.gateway.exception;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.study.gateway.common.CommonResult;
import com.study.gateway.common.ResultStatus;
import com.study.gateway.utils.JacksonUtil;

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
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @SuppressWarnings("rawtypes")
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex)
    {
        // 打印错误日志
        logger.error(ex.getMessage());
        
        /**封装返回的错误信息*/
        CommonResult result = new CommonResult();
        result.setCode(ResultStatus.ERROR.getCode());
        result.setMessage(ResultStatus.ERROR.getMessage());
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        
        // 将返回信息放入response
        DataBuffer wrap = response.bufferFactory().wrap(JacksonUtil.serialize(result).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(wrap));
    }
}
