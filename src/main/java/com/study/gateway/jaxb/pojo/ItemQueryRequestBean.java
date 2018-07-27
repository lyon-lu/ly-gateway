/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * ItemRequestXml.java created on Jul 17, 2018 9:03:58 AM by Lyon Lu 
 */
package com.study.gateway.jaxb.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

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
public class ItemQueryRequestBean extends BaseRequest
{
    /**
    * @fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = -4360521878582559588L;
    
    private Body Body;
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body
    {
        private ItemQueryRequest ItemQueryRequest;

        public ItemQueryRequest getItemQueryRequest()
        {
            return ItemQueryRequest;
        }

        public void setItemQueryRequest(ItemQueryRequest itemQueryRequest)
        {
            ItemQueryRequest = itemQueryRequest;
        }
    }
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ItemQueryRequest
    {
        private String CompanyCode;
        
        @XmlElementWrapper(name="SkuNoList") 
        private List<String> SkuNo;

        public List<String> getSkuNo()
        {
            return SkuNo;
        }

        public void setSkuNo(List<String> skuNo)
        {
            SkuNo = skuNo;
        }

        public String getCompanyCode()
        {
            return CompanyCode;
        }

        public void setCompanyCode(String companyCode)
        {
            CompanyCode = companyCode;
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
