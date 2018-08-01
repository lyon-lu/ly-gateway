/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * InputService.java created on Aug 1, 2018 4:35:35 PM by Lyon Lu 
 */
package com.study.gateway.input.service;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.server.ServerWebExchange;

import com.study.gateway.filter.DataProcessGatewayFilterFactory.Config;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Aug 1, 2018 4:35:35 PM
 *
 * </pre>
 */
public interface InputService
{
    DataBuffer internalCall(Config config, ServerWebExchange exchange);
}
