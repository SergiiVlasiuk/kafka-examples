package com.custom.converter.kafka.serializer;

import com.custom.converter.kafka.data.Bar;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class BarDeserializer implements Deserializer<Bar> {

    @Override
    public Bar deserialize(String arg0, byte[] devBytes) {
//	public Bar deserialize(String arg0, String devBytes) {
        ObjectMapper mapper = new ObjectMapper();
        Bar bar = null;
        try {
            bar = mapper.readValue(devBytes, Bar.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return bar;
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
