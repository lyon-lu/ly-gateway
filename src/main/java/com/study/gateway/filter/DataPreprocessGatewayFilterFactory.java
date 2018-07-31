/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * DataPreprocessGatewayFilterFactory.java created on Jul 19, 2018 2:56:37 PM by Lyon Lu 
 */
package com.study.gateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.study.gateway.client.InputClient;
import com.study.gateway.common.CharConstant;
import com.study.gateway.utils.JaxbUtil;

import reactor.core.publisher.Flux;
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
public class DataPreprocessGatewayFilterFactory extends AbstractGatewayFilterFactory<DataPreprocessGatewayFilterFactory.Config>
{
    @Resource
    private InputClient inputClient;
    
    public DataPreprocessGatewayFilterFactory()
    {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config)
    {
        return (exchange, chain) -> {
            
            ServerHttpRequest request = exchange.getRequest();
            
            String path = request.getURI().getPath();
            String methodName = StringUtils.substringAfterLast(path, CharConstant.SEPARATOR);
            
            Flux<DataBuffer> body = request.getBody();
            DataBuffer blockLast = body.blockLast();
            InputStream asInputStream = null;
            try
            {
                asInputStream = blockLast.asInputStream();
                String xmlResource = IOUtils.toString(asInputStream, StandardCharsets.UTF_8);
                
                String requestBeanName = config.getRequestBean().get(methodName);
                
                Class<?> calss = Class.forName(requestBeanName);
                Object xmlBean = JaxbUtil.converyToJavaBean(xmlResource, calss);
                
                Method findMethod = ReflectionUtils.findMethod(inputClient.getClass(), methodName, calss);
                
                Object responseData = ReflectionUtils.invokeMethod(findMethod, inputClient, xmlBean);
                String xmlStr = JaxbUtil.convertToXml(responseData);
                ServerHttpResponse response = exchange.getResponse();
                DataBuffer wrap = response.bufferFactory().wrap(xmlStr.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(wrap));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally 
            {
                try
                {
                    asInputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return chain.filter(exchange);
        };
    }
    public static class Config
    {
        private Map<String, String> requestBean = new HashMap<>();
        private Map<String, String> responseBean = new HashMap<>();
        public Map<String, String> getRequestBean()
        {
            return requestBean;
        }
        public void setRequestBean(Map<String, String> requestBean)
        {
            this.requestBean = requestBean;
        }
        public Map<String, String> getResponseBean()
        {
            return responseBean;
        }
        public void setResponseBean(Map<String, String> responseBean)
        {
            this.responseBean = responseBean;
        }
    }
}
