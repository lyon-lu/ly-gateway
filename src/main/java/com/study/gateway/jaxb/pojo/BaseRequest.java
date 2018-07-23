/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * BaseRequest.java created on Jul 17, 2018 8:57:25 AM by Lyon Lu 
 */
package com.study.gateway.jaxb.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 17, 2018 8:57:25 AM
 *
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseRequest implements Serializable
{
    /**
    * @fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = 6963315753097933251L;

    @XmlAttribute
    private String service;
    
    @XmlAttribute
    private String lang;
    
    public Head Head;
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Head
    {
        @XmlElement(required = true, defaultValue = "1234", nillable = true)
        private String AccessCode;
        
        private String Checkword;

        public String getAccessCode()
        {
            return AccessCode;
        }

        public void setAccessCode(String accessCode)
        {
            AccessCode = accessCode;
        }

        public String getCheckword()
        {
            return Checkword;
        }

        public void setCheckword(String checkword)
        {
            Checkword = checkword;
        }
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getLang()
    {
        return lang;
    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }

    public Head getHead()
    {
        return Head;
    }

    public void setHead(Head head)
    {
        Head = head;
    }
}
