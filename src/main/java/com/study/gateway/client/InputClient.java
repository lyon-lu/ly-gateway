/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TestClient.java created on Jul 23, 2018 8:37:51 AM by Lyon Lu 
 */
package com.study.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.gateway.client.fallback.InputClientFallback;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean;
import com.study.gateway.jaxb.pojo.ItemResponseBean;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 23, 2018 8:37:51 AM
 *
 * </pre>
 */
@FeignClient(name = "LY-SERVER", fallback = InputClientFallback.class)
public interface InputClient
{
    @RequestMapping(value = "input")
    ItemResponseBean input(@RequestBody ItemQueryRequestBean xml);
}
