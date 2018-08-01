/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TestClientFallback.java created on Jul 23, 2018 8:38:51 AM by Lyon Lu 
 */
package com.study.gateway.client.fallback;

import org.springframework.stereotype.Component;

import com.study.gateway.client.InputClient;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean;
import com.study.gateway.jaxb.pojo.ItemResponseBean;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 23, 2018 8:38:51 AM
 *
 * </pre>
 */
@Component
public class InputClientFallback extends AbstractFallback implements InputClient
{
    @Override
    public ItemResponseBean input(ItemQueryRequestBean query)
    {
        logger.error("input fallback");
        return null;
    }

}
