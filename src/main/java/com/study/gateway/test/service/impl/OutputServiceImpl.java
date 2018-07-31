/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * OutputServiceImpl.java created on Jul 31, 2018 11:22:21 AM by Lyon Lu 
 */
package com.study.gateway.test.service.impl;

import javax.annotation.Resource;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.study.gateway.jaxb.pojo.ItemResponseBean;
import com.study.gateway.test.service.OutputService;
import com.study.gateway.utils.JaxbUtil;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 31, 2018 11:22:21 AM
 *
 * </pre>
 */
@Service
public class OutputServiceImpl implements OutputService
{
    @Resource
    private RestTemplate restTemplate;
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T sfOutput(String xml, Class<T> clazz)
    {
        // 设置form参数
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("logistics_interface", xml);
        formData.add("data_digest", "hello world");
        
        // 设置headers
        HttpHeaders headers = new HttpHeaders();       
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        // 构造request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(formData, headers);     
        
        // 发送请求
        ResponseEntity<String> postForEntity = restTemplate.postForEntity("http://bsp.sit.sf-express.com:8080/bsp-wms/OmsCommons", request, String.class);
        
        // xml字符串转javaBean
        String body = postForEntity.getBody();
        T responseBean = (T) JaxbUtil.converyToJavaBean(body, ItemResponseBean.class);
        return responseBean;
    }

}
