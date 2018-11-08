package com.headers.example.kafka.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ObjectMapperUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T parseFromObjectJson(Object oldValue, Class<T> classValue) {
        T result = null;
        try {
            result = mapper.convertValue(oldValue, classValue);
        } catch (Exception e) {
            log.error("Can not convert <{}> to java object <{}>.", oldValue, classValue, e);
        }
        return result;
    }

    public static <T> T parseFromJson(String jsonString, Class<T> classValue) {
        T result = null;
        try {
            result = mapper.readValue(jsonString, classValue);
        } catch (IOException e) {
            log.error("Can not convert <{}> to {} object.", jsonString, classValue, e);
        }
        return result;
    }

    public static <T> T parseFromJson(byte[] jsonString, Class<T> classValue) {
        T result = null;
        try {
            result = mapper.readValue(jsonString, classValue);
        } catch (IOException e) {
            log.error("Can not convert <{}> to {} object.", jsonString, classValue, e);
        }
        return result;
    }

}
