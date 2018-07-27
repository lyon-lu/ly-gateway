/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * OutputResource.java created on Jul 23, 2018 4:41:27 PM by Lyon Lu 
 */
package com.study.gateway.test.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.study.gateway.jaxb.pojo.BaseRequest.Head;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean.Body;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean.ItemQueryRequest;
import com.study.gateway.jaxb.pojo.ItemResponseBean;
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
    @Resource
    private RestTemplate restTemplate;
    
    
    @RequestMapping(value = "output")
    public Mono<ItemResponseBean> output(@RequestBody ItemQueryRequestBean xml)
    {
        ItemQueryRequestBean xmlBean = new ItemQueryRequestBean();
        xmlBean.setLang("zh-CN");
        xmlBean.setService("ITEM_QUERY_SERVICE");
        
        Head head = new Head();
        head.setAccessCode("bvW2Fcx8Hb1OYzZaL2mY8A==");
        head.setCheckword("Pa2zfPtcCIvmhxYkfw5Bj6JgPmx63s4i");
        xmlBean.setHead(head);
        
        ItemQueryRequest item = new ItemQueryRequest();
        List<String> skuNoList = new ArrayList<>();
        skuNoList.add("1");
        item.setCompanyCode("COMMONCOMPANY");
        item.setSkuNo(skuNoList);
        
        Body body = new Body();
        body.setItemQueryRequest(item);
        xmlBean.setBody(body);
        String xmlStr = JaxbUtil.convertToXml(xmlBean);
        
        
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("logistics_interface", xmlStr);
        formData.add("data_digest", "hello world");
        
        return WebClient.create().post().uri("http://bsp.sit.sf-express.com:8080/bsp-wms/OmsCommons")
                .contentType(MediaType.APPLICATION_ATOM_XML)
                .body(BodyInserters.fromFormData(formData))
                .exchange().flatMap(x -> {
                    Mono<ItemResponseBean> bodyToMono = x.bodyToMono(ItemResponseBean.class);
                    return bodyToMono;
                });
       
                /*final Function<Boolean, Health.Builder> healthBuilder = bool -> bool ? Health.up() : Health.down();
                
                final Function<Throwable, Health.Builder> throwableBuilder = e -> Health.down(new RuntimeException(e));
                
                final CompletableFuture<Health> subscription = status.thenApply(HttpStatus::is2xxSuccessful)
                .thenApply(healthBuilder)
                .exceptionally(throwableBuilder)
                .thenApply(Health.Builder::build);
                
                final Function<Throwable, Health> health = e -> throwableBuilder.apply(e)
                       .build();
                return Try.of(subscription::get)
                .getOrElseGet(health);*/
        /*String xmlStr = JaxbUtil.convertToXml(xml);
        
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("logistics_interface", xmlStr);
        formData.add("data_digest", "hello world");
        
        Mono<String> bodyToMono = WebClient.create().post().uri("http://bsp.sit.sf-express.com:8080/bsp-wms/OmsCommons")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(BodyInserters.fromObject(formData))
        .retrieve().bodyToMono(String.class);
        
        
        System.out.println("end");
        return null;*/
    }
}
