package com.custom.converter.kafka;

import com.custom.converter.kafka.data.Bar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "${app.topic.foo}")
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @KafkaHandler
//    public void receive(@Payload String message,
    public void receive(@Payload Bar message,
                        @Headers MessageHeaders headers) {
        LOG.info("[Bar] received message='{}'", message);
        headers.keySet().forEach(key -> LOG.info("{}: {}", key, headers.get(key)));
    }

    @KafkaHandler(isDefault = true)
    public void receiveDefault(@Payload String message,
                               @Headers MessageHeaders headers) {
        LOG.info("[default] received message='{}'", message);
        headers.keySet().forEach(key -> LOG.info("{}: {}", key, headers.get(key)));
    }


}