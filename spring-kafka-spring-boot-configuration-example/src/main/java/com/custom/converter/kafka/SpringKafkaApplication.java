package com.custom.converter.kafka;

import com.custom.converter.kafka.data.Bar;
import com.custom.converter.kafka.data.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaApplication implements CommandLineRunner {

    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        sender.send("Spring Kafka and Spring Boot Configuration Example");
        sender.send(new Bar("Spring Kafka and Spring Boot Configuration Example"));
//        sender.send(new Foo("Spring Kafka and Spring Boot Configuration Example"));
    }
}