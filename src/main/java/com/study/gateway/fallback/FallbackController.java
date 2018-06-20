/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * FallbackController.java created on Jun 6, 2018 6:28:57 PM by Lyon Lu 
 */
package com.study.gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 6, 2018 6:28:57 PM
 *
 * </pre>
 */

@RestController
public class FallbackController
{
    @RequestMapping("/hystrixfallback")
    public String hystrixfallback() 
    {
        System.out.println("fallback ----------------------");
        return "This is a fallback";
    }
    
    @RequestMapping(value = "/fallback")
    public Mono<String> fallback() 
    {
        System.out.println("fallback ---------------------");
        return Mono.just("fallback");
    }
}
