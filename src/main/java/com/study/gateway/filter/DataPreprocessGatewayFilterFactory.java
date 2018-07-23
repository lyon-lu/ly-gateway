/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * DataPreprocessGatewayFilterFactory.java created on Jul 19, 2018 2:56:37 PM by Lyon Lu 
 */
package com.study.gateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.study.gateway.client.InputClient;
import com.study.gateway.jaxb.pojo.ItemRequestXml;
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
            Flux<DataBuffer> body = request.getBody();
            DataBuffer blockLast = body.blockLast();
            InputStream asInputStream = null;
            try
            {
                asInputStream = blockLast.asInputStream();
                String string = IOUtils.toString(asInputStream, StandardCharsets.UTF_8);
                ItemRequestXml converyToJavaBean = JaxbUtil.converyToJavaBean(string, ItemRequestXml.class);
                ItemRequestXml input = inputClient.input(converyToJavaBean);
                String convertToXml = JaxbUtil.convertToXml(input);
                
                System.out.println(convertToXml);
                ServerHttpResponse response = exchange.getResponse();
                DataBuffer wrap = response.bufferFactory().wrap(convertToXml.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(wrap));
            }
            catch (IOException e)
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

    }

    

}
