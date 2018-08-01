/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * OutputResource.java created on Jul 23, 2018 4:41:27 PM by Lyon Lu 
 */
package com.study.gateway.output.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.gateway.jaxb.pojo.BaseRequest.Head;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean.Body;
import com.study.gateway.jaxb.pojo.ItemQueryRequestBean.ItemQueryRequest;
import com.study.gateway.jaxb.pojo.ItemResponseBean;
import com.study.gateway.jaxb.pojo.ItemResponseBean.ItemResponse;
import com.study.gateway.output.service.OutputService;
import com.study.gateway.utils.JaxbUtil;

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
    private OutputService outputService;
    
    @RequestMapping(value = "output")
    public ItemResponse output(@RequestBody ItemQueryRequest query)
    {
        ItemQueryRequestBean bean = new ItemQueryRequestBean();
        bean.setLang("zh-CN");
        bean.setService("ITEM_QUERY_SERVICE");
        
        Head head = new Head();
        head.setAccessCode("bvW2Fcx8Hb1OYzZaL2mY8A==");
        head.setCheckword("Pa2zfPtcCIvmhxYkfw5Bj6JgPmx63s4i");
        bean.setHead(head);
        
        Body body = new Body();
        body.setItemQueryRequest(query);
        bean.setBody(body);
        
        String xmlStr = JaxbUtil.convertToXml(bean);
        
        ItemResponseBean output = outputService.omsOutput(xmlStr,ItemResponseBean.class);
        ItemResponse itemResponse = null;
        if(null != output)
        {
            String resHead = output.getHead();
            if("OK".equalsIgnoreCase(resHead))
            {
                itemResponse = output.getBody().getItemResponse();
            }
            else 
            {
                // 记录错误信息
            }
        }
        return itemResponse;
    }
}
