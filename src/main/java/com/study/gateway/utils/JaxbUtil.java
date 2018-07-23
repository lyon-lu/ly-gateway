/**
 * Copyright (C) Skywares Information Technology, LTD. 
 * All Rights Reserved.
 *
 * JaxbUtil.java created on Jul 17, 2018 3:17:05 PM by Lyon Lu 
 */
package com.study.gateway.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * <pre>
 * Description:
 * 
 * @author Lyon Lu
 * @date Jul 17, 2018 3:23:43 PM
 *
 * </pre>
 */
public class JaxbUtil
{
    /**
     * Description:
     * 		javaBean转xml, 默认编码为UTF-8
     * @param obj
     * @return String      
     * @throws
     */
    public static String convertToXml(Object obj)
    {
        return convertToXml(obj, "UTF-8");
    }

    /**
     * Description:
     * 		javaBean转xml
     * @param obj
     * @param encoding
     * @return
     * @return String      
     * @throws
     */
    public static String convertToXml(Object obj, String encoding)
    {
        String result = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();

            // 转换成xml时进行格式化（自动换行等）
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // xml的编码方式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Description:
     * 		xml转javaBean
     * @param xml
     * @param c
     * @return T      
     * @throws
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c)
    {
        T t = null;
        try
        {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return t;
    }
}
