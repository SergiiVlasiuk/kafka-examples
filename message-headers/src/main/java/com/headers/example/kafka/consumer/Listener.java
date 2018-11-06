package com.headers.example.kafka.consumer;

import com.headers.example.kafka.data.Bar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "${app.topic.bar}")
public class Listener {

    private static final Logger LOG = LoggerFactory.getLogger(Listener.class);

    @KafkaHandler
    public void receive(@Payload Bar data,
                        @Headers MessageHeaders messageHeaders) {

        LOG.info("- - - - - - - - - - - - - - -");
        LOG.info("[receiveBar][bar]received message='{}'", data);
        messageHeaders.keySet().forEach(key -> {
            Object value = messageHeaders.get(key);
                LOG.info("{}: {}", key, value);
        });

    }

    @KafkaHandler(isDefault = true)
    public void receiveString(@Payload String data,
                        @Headers MessageHeaders messageHeaders) {

        LOG.info("- - - - - - - - - - - - - - -");
        LOG.info("[receiveString][bar]received message='{}'", data);
        messageHeaders.keySet().forEach(key -> {
            Object value = messageHeaders.get(key);
                LOG.info("{}: {}", key, value);
        });

    }
}
