package com.headers.example.kafka.consumer;

import com.headers.example.kafka.data.Foo;
import com.headers.example.kafka.serializer.AbstractEventDeserializer;
import com.headers.example.kafka.data.Bar;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ListenerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AbstractEventDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        Map<String, Class> map = new HashMap<>();
        map.put("bar", Bar.class);
        map.put("foo", Foo.class);
        props.put("mappers", map);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Bar> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Bar>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Bar> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public DefaultKafkaHeaderMapper headerMapper(){
        return new DefaultKafkaHeaderMapper();
    }

}
