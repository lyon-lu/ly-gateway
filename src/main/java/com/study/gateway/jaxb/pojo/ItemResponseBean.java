/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * GoodsResonseBody.java created on Jul 12, 2018 9:28:38 AM by Lyon Lu 
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
 * @date Jul 12, 2018 9:28:38 AM
 *
 * </pre>
 */
@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemResponseBean extends BaseResponse
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
        private String CompanyCode;
        private String Result;
        private String Remark;
        private Integer TotalSize;
        
        @XmlElementWrapper(name="Items") 
        private List<Item> Item;

        public String getCompanyCode()
        {
            return CompanyCode;
        }

        public void setCompanyCode(String companyCode)
        {
            CompanyCode = companyCode;
        }

        public String getResult()
        {
            return Result;
        }

        public void setResult(String result)
        {
            Result = result;
        }

        public String getRemark()
        {
            return Remark;
        }

        public void setRemark(String remark)
        {
            Remark = remark;
        }

        public Integer getTotalSize()
        {
            return TotalSize;
        }

        public void setTotalSize(Integer totalSize)
        {
            TotalSize = totalSize;
        }

        public List<Item> getItem()
        {
            return Item;
        }

        public void setItem(List<Item> item)
        {
            Item = item;
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
