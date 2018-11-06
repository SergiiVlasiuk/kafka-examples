package com.custom.converter.kafka.serializer;

import com.custom.converter.kafka.data.Bar;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class BarSerializer implements Serializer<Bar> {

    @Override
    public byte[] serialize(String arg0, Bar developer) {
        byte[] serializedBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//			serializedBytes = objectMapper.writeValueAsString(developer).getBytes("UTF-8");
            serializedBytes = objectMapper.writeValueAsString(developer).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serializedBytes;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
        // TODO Auto-generated method stub
    }
}
