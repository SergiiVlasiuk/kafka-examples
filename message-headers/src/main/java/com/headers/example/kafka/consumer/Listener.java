package com.headers.example.kafka.consumer;

import com.headers.example.kafka.data.Bar;
import com.headers.example.kafka.data.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(topics = "${app.topic.bar}", id = "multi")
public class Listener {

    @KafkaHandler
    public void receive(@Payload Bar data,
                        @Headers MessageHeaders messageHeaders) {
        log.info("- - - - - - - - - - - - - - - receive BAR object - - - - - - - - - - - - - - -");
        log.info("received message='{}'", data);
        logHeaders(messageHeaders);
    }

    @KafkaHandler
    public void receive(@Payload Foo data,
                        @Headers MessageHeaders messageHeaders) {
        log.info("- - - - - - - - - - - - - - - receive FOO object - - - - - - - - - - - - - - -");
        log.info("received message='{}'", data);
        logHeaders(messageHeaders);
    }

    @KafkaHandler(isDefault = true)
    public void receiveString(@Payload String data,
                              @Headers MessageHeaders messageHeaders) {
        log.info("- - - - - - - - - - - - - - - receive String object - - - - - - - - - - - - - - -");
        log.info("received message='{}'", data);
        logHeaders(messageHeaders);
    }

    private void logHeaders(@Headers MessageHeaders messageHeaders) {
        log.info("- - - - - - - - - - - - - - - HEADERS - - - - - - - - - - - - - - -");
        messageHeaders.keySet().forEach(key -> {
            Object value = messageHeaders.get(key);
            log.info("{}: {}", key, value);
        });
    }

}
