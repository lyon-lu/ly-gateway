/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * RequestRateLimiterGatewayFilterFactory.java created on Jun 13, 2018 10:44:39 AM by Lyon Lu 
 */
package com.study.gateway.filter;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.study.gateway.common.CommonResult;
import com.study.gateway.common.ResultStatus;
import com.study.gateway.utils.JacksonUtil;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 15, 2018 10:00:29 AM
 *
 * </pre>
 */
@Component
public class RequestRateLimiterCustomGatewayFilterFactory extends RequestRateLimiterGatewayFilterFactory
{

    private Logger logger = LogManager.getLogger(this.getClass());

    @SuppressWarnings("rawtypes")
    public RequestRateLimiterCustomGatewayFilterFactory(RateLimiter defaultRateLimiter,
            @Qualifier(value = "tokenInfoKeyResolver") KeyResolver defaultKeyResolver)
    {
        super(defaultRateLimiter, defaultKeyResolver);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public GatewayFilter apply(Config config)
    {
        KeyResolver resolver = (config.getKeyResolver() == null) ? getDefaultKeyResolver() : config.getKeyResolver();
        RateLimiter<Object> limiter = (config.getRateLimiter() == null) ? getDefaultRateLimiter()
                : config.getRateLimiter();

        return (exchange, chain) -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

            return resolver.resolve(exchange).flatMap(key ->
            // TODO: if key is empty?
            limiter.isAllowed(route.getId(), key).flatMap(response -> {
                // TODO: set some headers for rate, tokens left
                if (response.isAllowed())
                {
                    return chain.filter(exchange);
                }
                exchange.getResponse().setStatusCode(HttpStatus.OK);
                
                /** 设置返回信息 */
                CommonResult result = new CommonResult();
                result.setCode(ResultStatus.SYSTEM_BUSY.getCode());
                result.setMessage(ResultStatus.SYSTEM_BUSY.getMessage());
                DataBuffer wrap = exchange.getResponse().bufferFactory()
                        .wrap(JacksonUtil.serialize(result).getBytes(StandardCharsets.UTF_8));

                // 打印日志
                logger.error("The system is busy");

                return exchange.getResponse().writeWith(Mono.just(wrap));
            }));
        };
    }
}
