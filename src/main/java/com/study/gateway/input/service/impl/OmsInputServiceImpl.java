/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * OmsInputServiceImpl.java created on Aug 1, 2018 4:59:37 PM by Lyon Lu 
 */
package com.study.gateway.input.service.impl;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ServerWebExchange;

import com.study.gateway.client.OmsInputClient;
import com.study.gateway.common.CharConstant;
import com.study.gateway.filter.DataProcessGatewayFilterFactory.Config;
import com.study.gateway.input.service.InputService;
import com.study.gateway.utils.JaxbUtil;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Aug 1, 2018 4:59:37 PM
 *
 * </pre>
 */
@Service(value = "omsInputService")
public class OmsInputServiceImpl implements InputService
{
    @Resource
    private OmsInputClient omsInputClient;
    
    @Override
    public DataBuffer internalCall(Config config, ServerWebExchange exchange)
    {
        /**获取请求参数并转换成内部服务的参数类型*/
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> formData = exchange.getFormData().block();
        String xmlResource = formData.getFirst("logistics_interface");
        String path = request.getURI().getPath();
        
        // 获取url方法名
        String methodName = StringUtils.substringAfterLast(path, CharConstant.SEPARATOR);
        
        /**调用内部服务*/
        // 获取此次请求需要调用的方法
        Method findMethod = null;
        Method[] allMethods = ReflectionUtils.getAllDeclaredMethods(omsInputClient.getClass());
        for(Method method : allMethods)
        {
            if(method.getName().equalsIgnoreCase(methodName))
            {
                findMethod = method;
                break;
            }
        }
        
        if(null != findMethod)
        {
            Class<?>[] parameterTypes = findMethod.getParameterTypes();
            
            // 请求参数转为java bean
            Object xmlBean = JaxbUtil.converyToJavaBean(xmlResource, parameterTypes[0]);
            
            // 调用service服务接口
            Object responseData = ReflectionUtils.invokeMethod(findMethod, omsInputClient, xmlBean);
            
            /**返回结果转换*/
            String xmlStr = JaxbUtil.convertToXml(responseData);
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer wrap = response.bufferFactory().wrap(xmlStr.getBytes(StandardCharsets.UTF_8));
            
            return wrap;
        }
        
        return null;
    }

}
