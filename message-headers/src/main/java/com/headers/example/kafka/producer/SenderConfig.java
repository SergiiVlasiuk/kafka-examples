package com.headers.example.kafka.producer;

import com.headers.example.kafka.data.Bar;
import com.headers.example.kafka.data.Foo;
import com.headers.example.kafka.serializer.AbstractEventSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SenderConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AbstractEventSerializer.class);
        Map<String, Class> map = new HashMap<>();
        map.put("bar", Bar.class);
        map.put("foo", Foo.class);
        props.put("mappers", map);
        return props;
    }

    @Bean
    public ProducerFactory<String, Bar> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Bar> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
