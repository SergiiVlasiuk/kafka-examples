package com.headers.example.kafka.producer;

import com.headers.example.kafka.data.Bar;
import com.headers.example.kafka.data.Foo;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${app.topic.foo}")
    private String topicFoo;

    @Value("${app.topic.bar}")
    private String topicBar;

    public void sendBar(String data) {

        List<Header> headers = new ArrayList();
        headers.add(new RecordHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka example".getBytes()));
        headers.add(new RecordHeader("X-CLASS-TYPE", "bar".getBytes()));
        ProducerRecord<String, Bar> bar = new ProducerRecord(topicBar, 0, "111", new Bar(data), headers);
        LOG.info("sending BAR message='{}' to topic='{}'", data, topicBar);
        kafkaTemplate.send(bar);
    }

    public void sendFoo(String data) {
        List<Header> headers = new ArrayList();
        headers = new ArrayList<>();
        headers.add(new RecordHeader("X-CLASS-TYPE", "foo".getBytes()));
        ProducerRecord<String, Foo> foo = new ProducerRecord(topicBar, 0, "111", new Foo(data), headers);
        LOG.info("sending FOO message='{}' to topic='{}'", data, topicBar);
        kafkaTemplate.send(foo);
    }

    public void sendFooInDifferentWay(String data) {
        Message<Foo> message = MessageBuilder
                .withPayload(new Foo(data + "_suffix"))
                .setHeader(KafkaHeaders.TOPIC, topicBar)
                .setHeader(KafkaHeaders.MESSAGE_KEY, "999")
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .setHeader("X-Custom-Header", "Sending Custom Header with Spring Kafka".getBytes())
                .setHeader("X-CLASS-TYPE", "foo".getBytes())
                .build();
        kafkaTemplate.send(message);
    }
}
