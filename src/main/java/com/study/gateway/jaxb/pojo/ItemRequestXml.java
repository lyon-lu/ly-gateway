/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * ItemRequestXml.java created on Jul 17, 2018 9:03:58 AM by Lyon Lu 
 */
package com.study.gateway.jaxb.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.study.gateway.jaxb.pojo.BaseRequest;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 17, 2018 9:03:58 AM
 *
 * </pre>
 */
@XmlRootElement(name="Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemRequestXml extends BaseRequest
{
    /**
    * @fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = -4360521878582559588L;
    
    private Body Body;
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body
    {
        private ItemRequest ItemRequest;

        public ItemRequest getItemRequest()
        {
            return ItemRequest;
        }

        public void setItemRequest(ItemRequest itemRequest)
        {
            ItemRequest = itemRequest;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ItemRequest
    {
        private String code;

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }
    }

    public Body getBody()
    {
        return Body;
    }

    public void setBody(Body body)
    {
        Body = body;
    }
}
