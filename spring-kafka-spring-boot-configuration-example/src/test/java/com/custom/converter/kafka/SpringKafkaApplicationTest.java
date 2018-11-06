package com.custom.converter.kafka;

import com.custom.converter.kafka.data.Bar;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class SpringKafkaApplicationTest {

    private static final String EXAMPLE_TOPIC = "foo.t";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, EXAMPLE_TOPIC);

    @Autowired
    private Sender sender;

    @Test(expected = SerializationException.class)
    public void testReceive() {
        sender.send("Spring Kafka and Spring Boot Configuration Example");
    }

    @Test
    public void testReceiveBar() {
        sender.send(new Bar("Spring Kafka and Spring Boot Configuration Example"));
    }

}
