/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * CommonResult.java created on 23/6/2017 10:28:10 by Sandy Deng 
 */
package com.study.gateway.common;

import java.io.Serializable;

/**
 * <pre>
 * Description:
 * 公共結果集
 * @author Sandy Deng
 * @date 23/6/2017 10:28:10
 *
 * </pre>
 */
public class CommonResult<T> implements Serializable
{
    private static final long serialVersionUID = 7039575865327218684L;
    private int code;
    private String message;
    private T data;
    
    public CommonResult()
    {
    }

    public int getCode() 
    {
        return code;
    }

    public void setCode(int code) 
    {
        this.code = code;
    }

    public String getMessage() 
    {
        return message;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public T getData() 
    {
        return data;
    }

    public void setData(T data) 
    {
        this.data = data;
    }

    public CommonResult(ResultStatus resultStatus, T data) 
    {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommonResult{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
