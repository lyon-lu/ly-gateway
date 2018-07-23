/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * GoodsResonseBody.java created on Jul 12, 2018 9:28:38 AM by Lyon Lu 
 */
package com.study.gateway.jaxb.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 12, 2018 9:28:38 AM
 *
 * </pre>
 */
@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemResponseXml extends BaseResponse
{
    /**
    * @fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = 6437379031801938017L;
    
    private Body Body;
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body
    {
        private ItemResponse ItemResponse;

        public ItemResponse getItemResponse()
        {
            return ItemResponse;
        }

        public void setItemResponse(ItemResponse itemResponse)
        {
            ItemResponse = itemResponse;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ItemResponse
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
