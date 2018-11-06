package com.custom.converter.kafka;

import com.custom.converter.kafka.data.Bar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.custom.converter.kafka.data.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${app.topic.foo}")
    private String topic;

    private static <T> String getData(T message) {
//        if (message instanceof String
//                || message instanceof Integer
//                || message instanceof Long) {
//            return (String)message;
//        }
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

//    public void send(Object message){
//        LOG.info("sending message='{}' to topic='{}'", message, topic);
//        kafkaTemplate.send(topic, getData(message));
//    }

    public void send(Bar message) {
        LOG.info("sending message='{}' to topic='{}'", message, topic);
//        kafkaTemplate.send(topic, getData(message));
        kafkaTemplate.send(topic, message);
    }

    public void send(Foo message) {
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, getData(message));
    }

    public void send(String message) {
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
