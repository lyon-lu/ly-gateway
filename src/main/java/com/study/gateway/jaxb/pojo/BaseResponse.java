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
