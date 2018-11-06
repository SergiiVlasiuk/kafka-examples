package com.headers.example.kafka.producer;

import com.headers.example.kafka.data.Bar;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Sender {
//public class Sender<T extends Object> {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, Bar> kafkaTemplate;
//    private KafkaTemplate kafkaTemplate;

    @Value("${app.topic.foo}")
    private String topicFoo;

    @Value("${app.topic.bar}")
    private String topicBar;

//    public void sendFoo(String data){
//
//       Message<String> message = MessageBuilder
//                .withPayload(data)
//                .setHeader(KafkaHeaders.TOPIC, topicFoo)
//                .setHeader(KafkaHeaders.MESSAGE_KEY, "999")
//                .setHeader(KafkaHeaders.PARTITION_ID, 0)
////                .setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka")
//                .build();
//
//        LOG.info("sending message='{}' to topic='{}'", data, topicFoo);
//        kafkaTemplate.send(message);
//    }

    public void sendBar(String data){

        List<Header> headers = new ArrayList<>();
//        headers.add(new RecordHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka".getBytes()));

        ProducerRecord<String, Bar> bar = new ProducerRecord<>(topicBar, 0, "111", new Bar(data), headers);
        LOG.info("sending message='{}' to topic='{}'", data, topicBar);

        kafkaTemplate.send(bar);
    }
}
