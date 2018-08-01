/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * DataPreprocessGatewayFilterFactory.java created on Jul 19, 2018 2:56:37 PM by Lyon Lu 
 */
package com.study.gateway.filter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Component;

import com.study.gateway.client.OmsInputClient;
import com.study.gateway.common.CharConstant;
import com.study.gateway.input.service.InputService;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 19, 2018 2:56:37 PM
 *
 * </pre>
 */
@Component
public class DataProcessGatewayFilterFactory extends AbstractGatewayFilterFactory<DataProcessGatewayFilterFactory.Config>
{
    @Resource
    private OmsInputClient omsInputClient;
    
    public DataProcessGatewayFilterFactory()
    {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config)
    {
        return (exchange, chain) -> {
            
            String path = exchange.getRequest().getURI().getPath();
            String[] pathArr = StringUtils.split(path, CharConstant.SEPARATOR);
            
            // 获取当前调用系统名称
            InputService inputService = config.getCallerService().get(pathArr[1]);
            
            // 获取当前系统的处理service
            DataBuffer data = inputService.internalCall(config, exchange);
            return exchange.getResponse().writeWith(Mono.just(data));
        };
    }
    
    public static class Config
    {
        private Map<String, InputService> callerService = new HashMap<>();

        public Map<String, InputService> getCallerService()
        {
            return callerService;
        }

        public void setCallerService(Map<String, InputService> callerService)
        {
            this.callerService = callerService;
        }
    }
}
