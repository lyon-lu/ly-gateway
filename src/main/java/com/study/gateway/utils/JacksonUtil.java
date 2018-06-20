package com.study.gateway.utils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JacksonUtil
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LogManager.getLogger(JacksonUtil.class);

    public static String serialize(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("serialize JSON failed.", e);
        }
        return null;
    }

    public static void serialize(Object obj, File file) {
        try {
            MAPPER.writeValue(file, obj);
        } catch (Exception e) {
            LOGGER.error("serialize JSON failed.", e);
        }
    }

    public static void serialize(Object obj, OutputStream out) {
        try {
            MAPPER.writeValue(out, obj);
        } catch (Exception e) {
            LOGGER.error("serialize JSON failed.", e);
        }
    }

    public static void serialize(Object obj, Writer out) {
        try {
            MAPPER.writeValue(out, obj);
        } catch (Exception e) {
            LOGGER.error("serialize JSON failed.", e);
        }
    }

    public static <T> T deserialize(String data, Class<T> clazz) {
        try {
            return MAPPER.readValue(data, clazz);
        } catch (Exception e) {
            LOGGER.error("deserialize JSON failed.", e);
        }
        return null;
    }

    public static <T> T deserialize(String data, TypeReference<?> ref) {
        try {
            return MAPPER.readValue(data, ref);
        } catch (Exception e) {
            LOGGER.error("deserialize JSON failed.", e);
        }
        return null;
    }

    public static <T> T deserialize(File file, Class<T> clazz) {
        try {
            return MAPPER.readValue(file, clazz);
        } catch (Exception e) {
            LOGGER.error("deserialize JSON failed.", e);
        }
        return null;
    }

    public static <T> T deserialize(InputStream in, Class<T> clazz) {
        try {
            return MAPPER.readValue(in, clazz);
        } catch (Exception e) {
            LOGGER.error("deserialize JSON failed.", e);
        }
        return null;
    }

    public static <T> T deserialize(Reader in, Class<T> clazz) {
        try {
            return MAPPER.readValue(in, clazz);
        } catch (Exception e) {
            LOGGER.error("deserialize JSON failed.", e);
        }
        return null;
    }
}
