/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * OutputResource.java created on Jul 23, 2018 4:41:27 PM by Lyon Lu 
 */
package com.study.gateway.test.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.study.gateway.jaxb.pojo.ItemRequestXml;
import com.study.gateway.jaxb.pojo.ItemResponseXml;
import com.study.gateway.utils.JaxbUtil;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 23, 2018 4:41:27 PM
 *
 * </pre>
 */
@RestController
public class OutputResource
{
    @RequestMapping(value = "output")
    public ItemResponseXml output(@RequestBody ItemRequestXml xml)
    {
        String xmlStr = JaxbUtil.convertToXml(xml);
        
        Mono<String> bodyToMono = WebClient.create().post().uri("http://localhost:8082/xml")
        .contentType(MediaType.APPLICATION_XML)
        .body(BodyInserters.fromObject(xmlStr))
        .retrieve().bodyToMono(String.class);
        
        String block = bodyToMono.block();
        //ItemResponseXml xmlBean = JaxbUtil.converyToJavaBean(block, ItemResponseXml.class);
        
        return null;
    }
}
