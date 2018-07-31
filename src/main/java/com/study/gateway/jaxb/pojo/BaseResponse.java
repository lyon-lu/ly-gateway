/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * BaseResponse.java created on Jul 17, 2018 2:25:45 PM by Lyon Lu 
 */
package com.study.gateway.jaxb.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 17, 2018 2:25:45 PM
 *
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResponse implements Serializable
{
    /**
    * @fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = 7601547677977654596L;
    
    @XmlAttribute
    private String service;
    
    @XmlAttribute
    private String lang;
    
    private String Head;
    
    private Error Error;
    
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Error
    {
        @XmlAttribute
        private String code;

        @XmlValue
        private String errorInfo;
        
        public String getErrorInfo()
        {
            return errorInfo;
        }

        public void setErrorInfo(String errorInfo)
        {
            this.errorInfo = errorInfo;
        }

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }
        
    }
    
    public Error getError()
    {
        return Error;
    }

    public void setError(Error error)
    {
        Error = error;
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

    public String getHead()
    {
        return Head;
    }

    public void setHead(String head)
    {
        Head = head;
    }
}
