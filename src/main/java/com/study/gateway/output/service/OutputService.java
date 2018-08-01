/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * OutputService.java created on Jul 31, 2018 11:22:00 AM by Lyon Lu 
 */
package com.study.gateway.output.service;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 31, 2018 11:22:00 AM
 *
 * </pre>
 */
public interface OutputService
{
    <T> T omsOutput(String xml, Class<T> clazz);
}
