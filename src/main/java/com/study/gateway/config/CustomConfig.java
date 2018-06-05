package com.study.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.study.gateway.pojo.TokenInfo;
/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jun 5, 2018 6:14:03 PM
 *
 * </pre>
 */
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {
	
	private int replenishRate;
	
	private int burstCapacity;
	
	private List<TokenInfo> tokenList = new ArrayList<>();
	
	public int getReplenishRate() {
		return replenishRate;
	}
	public void setReplenishRate(int replenishRate) {
		this.replenishRate = replenishRate;
	}
	public int getBurstCapacity() {
		return burstCapacity;
	}
	public void setBurstCapacity(int burstCapacity) {
		this.burstCapacity = burstCapacity;
	}
	public List<TokenInfo> getTokenList() {
		return tokenList;
	}
	public void setTokenList(List<TokenInfo> tokenList) {
		this.tokenList = tokenList;
	}
}
