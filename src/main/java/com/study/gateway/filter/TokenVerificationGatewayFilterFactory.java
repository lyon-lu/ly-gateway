/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * TokenVerificationGatewayFilterFactory.java created on Jun 6, 2018 9:55:51 AM by Lyon Lu 
 */
package com.study.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.study.gateway.common.CommonResult;
import com.study.gateway.common.ResultStatus;
import com.study.gateway.common.TokenInfo;
import com.study.gateway.config.CustomConfig;
import com.study.gateway.exception.SysException;
import com.study.gateway.utils.JacksonUtil;
import com.study.gateway.utils.ObjectUtil;

import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 6, 2018 9:55:51 AM
 *
 * </pre>
 */
@Component
public class TokenVerificationGatewayFilterFactory extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig>
{
    private Logger logger = LogManager.getLogger(this.getClass());
    
    @Resource
    private CustomConfig customConfig;
    
    public TokenVerificationGatewayFilterFactory()
    {
        super(NameConfig.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public GatewayFilter apply(NameConfig config)
    {
        return (exchange, chain) -> {
        	
            CommonResult result = new CommonResult();
            
            try
            {
                this.validateTokenInfo(exchange);
                return chain.filter(exchange);
            }
            catch (SysException e)
            {
                if(ResultStatus.INVALID_CHANNEL.getMessage().equals(e.getMessage()))
                {
                    // channel无效
                    result.setCode(ResultStatus.INVALID_CHANNEL.getCode());
                    result.setMessage(ResultStatus.INVALID_CHANNEL.getMessage());
                }
                else if (ResultStatus.INVALID_TOKEN.getMessage().equals(e.getMessage())) 
                {
                    // token无效
                    result.setCode(ResultStatus.INVALID_TOKEN.getCode());
                    result.setMessage(ResultStatus.INVALID_TOKEN.getMessage());
                }
                else 
                {
                    // 系统错误
                    result.setCode(ResultStatus.ERROR.getCode());
                    result.setMessage(ResultStatus.ERROR.getMessage());
                }
            }
            
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer wrap = response.bufferFactory().wrap(JacksonUtil.serialize(result).getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(wrap));
        };
    }

    /**
     * Description:
     * 		验证token信息
     * @param exchange
     * @return void      
     * @throws                                 
     */
    public void validateTokenInfo(ServerWebExchange exchange)
    {
        // 获取配置的token配置集合
        List<TokenInfo> tokenList = customConfig.getTokenList();
        if(ObjectUtil.isEmpty(tokenList))
        {
            logger.error("configured token is null");
            throw new SysException(ResultStatus.ERROR.getMessage());
        }
        
        // 每一组token信息中，token不重复。转为token为key，channel为value的map
        Map<String, String> tokenMap = tokenList.stream().collect(Collectors.toMap(TokenInfo::getAccessToken, TokenInfo::getChannelCode));
        
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String channel = headers.getFirst("channelCode");
        String token = headers.getFirst("accessToken");
        
        if(StringUtils.isBlank(token) || !tokenMap.containsKey(token))
        {
            // token为空或不存在
            logger.error("Invalidate accessToken");
            throw new SysException(ResultStatus.INVALID_TOKEN.getMessage());
        }
        
        if(StringUtils.isBlank(channel) || !channel.equalsIgnoreCase(tokenMap.get(token)))
        {
            // channel为空
            logger.error("Invalidate channelCode");
            throw new SysException(ResultStatus.INVALID_CHANNEL.getMessage());
        }
    }

}
