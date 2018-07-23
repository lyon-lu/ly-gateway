/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TestClientFallback.java created on Jul 23, 2018 8:38:51 AM by Lyon Lu 
 */
package com.study.gateway.client.fallback;

import org.springframework.stereotype.Component;

import com.study.gateway.client.InputClient;
import com.study.gateway.jaxb.pojo.ItemRequestXml;
import com.study.gateway.jaxb.pojo.ItemResponseXml;

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
    public ItemResponseXml input(ItemRequestXml xml)
    {
        logger.error("input fallback");
        return new ItemResponseXml();
    }

}
