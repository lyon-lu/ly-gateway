/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TokenInfo.java created on Jun 6, 2018 9:46:31 AM by Lyon Lu 
 */
package com.study.gateway.common;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 6, 2018 9:46:31 AM
 *
 * </pre>
 */
public class TokenInfo
{
    private String channelCode;
    private String accessToken;
    
    /**
     * @return the channelCode
     */
    public String getChannelCode()
    {
        return channelCode;
    }
    /**
     * @param channelCode the channelCode to set
     */
    public void setChannelCode(String channelCode)
    {
        this.channelCode = channelCode;
    }
    /**
     * @return the accessToken
     */
    public String getAccessToken()
    {
        return accessToken;
    }
    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
    
}
