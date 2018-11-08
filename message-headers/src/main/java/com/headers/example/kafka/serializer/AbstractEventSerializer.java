package com.headers.example.kafka.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.headers.example.kafka.data.AbstractEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class AbstractEventSerializer implements Serializer<AbstractEvent> {

    @Override
    public byte[] serialize(String arg0, AbstractEvent developer) {
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
