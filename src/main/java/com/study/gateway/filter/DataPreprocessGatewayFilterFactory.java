/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * DataPreprocessGatewayFilterFactory.java created on Jul 19, 2018 2:56:37 PM by Lyon Lu 
 */
package com.study.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import reactor.ipc.netty.NettyPipeline;
import reactor.ipc.netty.http.client.HttpClient;
import reactor.ipc.netty.http.client.HttpClientRequest;

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
public class DataPreprocessGatewayFilterFactory extends AbstractGatewayFilterFactory<DataPreprocessGatewayFilterFactory.Config>
{
    public DataPreprocessGatewayFilterFactory()
    {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config)
    {
        return (exchange, chain) -> {
            final DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders();
            ServerHttpRequest request = exchange.getRequest();
            HttpMethod method = HttpMethod.valueOf(request.getMethod().toString());
            HttpHeaders headers = request.getHeaders();
            headers.forEach(httpHeaders::set);
            String transferEncoding = headers.getFirst(HttpHeaders.TRANSFER_ENCODING);
            boolean chunkedTransfer = "chunked".equalsIgnoreCase(transferEncoding);
            HttpClient.create().request(method, "http://localhost:8082/test", req -> {
                final HttpClientRequest proxyRequest = req.options(NettyPipeline.SendOptions::flushOnEach)
                        .headers(httpHeaders)
                        .chunkedTransfer(chunkedTransfer)
                        .failOnServerError(false)
                        .failOnClientError(false);

                return proxyRequest.sendHeaders() //I shouldn't need this
                        .send(request.getBody().map(dataBuffer ->
                                ((NettyDataBuffer)dataBuffer).getNativeBuffer()));
            }).doOnNext(res -> {
                System.out.println(res);
            });
            
            return chain.filter(exchange);
        };
    }
    public static class Config
    {

    }

    

}
