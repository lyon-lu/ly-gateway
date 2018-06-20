/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * ResultStatus.java created on 23/6/2017 10:28:50 by Sandy Deng 
 */
package com.study.gateway.common;

/**
 * <pre>
 * Description:
 * @TODO file description here
 *
 * @author Sandy Deng
 * @date 23/6/2017 10:28:50
 *
 * </pre>
 */
public enum ResultStatus
{
    SUCCESS(200, "Success"),
    SYSTEM_BUSY(-1,"The system is busy"),
    HYSTRIX_TIMEOUT(504,"Hystrix timeout"),
    
    INVALID_TOKEN(30001,"Invalidate accessToken"),
    INVALID_CHANNEL(30002,"Invalidate channelCode"),
    
    ERROR(500, "Error");

    private final int code;
    private final String message;

    ResultStatus(int code, String message) 
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
