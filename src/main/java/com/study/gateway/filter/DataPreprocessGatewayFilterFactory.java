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
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.study.gateway.client.InputClient;
import com.study.gateway.common.CharConstant;
import com.study.gateway.jaxb.pojo.BaseRequest.Head;
import com.study.gateway.jaxb.pojo.ItemRequestXml;
import com.study.gateway.jaxb.pojo.ItemRequestXml.Body;
import com.study.gateway.jaxb.pojo.ItemRequestXml.ItemRequest;
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
            
            ItemRequestXml xml = new ItemRequestXml();
            xml.setLang("zh-CN");
            xml.setService("ITEM_QUERY_SERVICE");
            
            Head head = new Head();
            head.setAccessCode("qwerty");
            head.setCheckword("asdfgh");
            xml.setHead(head);
            
            ItemRequest item = new ItemRequest();
            item.setCode("123");
            
            Body body1 = new Body();
            body1.setItemRequest(item);
            xml.setBody(body1);
            
            String xmlStr1 = JaxbUtil.convertToXml(xml);
            
            
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("name1","value1");
            formData.add("name2","value2");
            Mono<String> bodyToMono = WebClient.create().post()
                    .uri("http://localhost:8082/success")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve().bodyToMono(String.class);
            
            String block = bodyToMono.block();
            System.out.println(block);
            
            
            
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
            /*MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("name1","value1");
            formData.add("name2","value2");
            
            Mono<String> bodyToMono = WebClient.create().post().uri("http://localhost:8082/input")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .retrieve().bodyToMono(String.class);
            
            String block = bodyToMono.block();
            System.out.println(block);*/
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
