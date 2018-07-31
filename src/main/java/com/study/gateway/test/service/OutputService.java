/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * OutputService.java created on Jul 31, 2018 11:22:00 AM by Lyon Lu 
 */
package com.study.gateway.test.service;

import com.study.gateway.jaxb.pojo.ItemResponseBean;

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
    ItemResponseBean output(String xml);
}
