/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * DataPreprocessGatewayFilterFactory.java created on Jul 19, 2018 2:56:37 PM by Lyon Lu 
 */
package com.study.gateway.filter;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import com.study.gateway.client.InputClient;
import com.study.gateway.common.CharConstant;
import com.study.gateway.utils.JaxbUtil;

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
            
            try
            {
                ServerHttpRequest request = exchange.getRequest();
                MultiValueMap<String, String> formData = exchange.getFormData().block();
                String xmlResource = formData.getFirst("logistics_interface");
                String path = request.getURI().getPath();
                String methodName = StringUtils.substringAfterLast(path, CharConstant.SEPARATOR);
                    
                // 获取传入参数类型
                String requestBeanName = config.getRequestBean().get(methodName);
                Class<?> calss = Class.forName(requestBeanName);
                // 请求参数转为java bean
                Object xmlBean = JaxbUtil.converyToJavaBean(xmlResource, calss);
                
                // 获取此次请求需要调用的方法
                Method findMethod = ReflectionUtils.findMethod(inputClient.getClass(), methodName, calss);
                
                // 调用service服务接口
                Object responseData = ReflectionUtils.invokeMethod(findMethod, inputClient, xmlBean);
                String xmlStr = JaxbUtil.convertToXml(responseData);
                ServerHttpResponse response = exchange.getResponse();
                DataBuffer wrap = response.bufferFactory().wrap(xmlStr.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(wrap));
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
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
