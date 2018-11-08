package com.headers.example.kafka.serializer;

import com.headers.example.kafka.data.AbstractEvent;
import com.headers.example.kafka.data.Bar;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.ExtendedDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.headers.example.kafka.utils.ObjectMapperUtil.parseFromJson;

@Slf4j
public class AbstractEventDeserializer implements ExtendedDeserializer<AbstractEvent> {

    private Map<String, Class<AbstractEvent>> mappers = new HashMap<>();

    @Override
    public AbstractEvent deserialize(String arg0, byte[] devBytes) {
        AbstractEvent bar = null;
        try {
            bar = parseFromJson(devBytes, Bar.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bar;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public AbstractEvent deserialize(String topic, Headers headers, byte[] data) {
        log.info("handling...");
        headers.forEach(header -> log.info("   {}: {}", header.key(), getHeaderValueAsString(header)));
        Optional<String> classTypeFromHeader = getClassTypeFromHeader(headers);
        if (classTypeFromHeader.isPresent()) {
            return parseFromJson(data, mappers.get(classTypeFromHeader.get()));
        }
        return deserialize(topic, data);
    }

    private Optional<String> getClassTypeFromHeader(Headers headers) {
        return StreamSupport.stream(headers.headers("X-CLASS-TYPE").spliterator(), false)
                .map(Header::value)
                .map(String::new)
                .findFirst();
    }

    private String getHeaderValueAsString(Header header) {
        return Optional.ofNullable(header.value())
                .map(String::new)
                .orElse(null);
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
        log.info("configs ===================");
        if (arg0.containsKey("mappers")) {
            this.mappers = (Map<String, Class<AbstractEvent>>) arg0.get("mappers");
        }
        arg0.keySet().forEach(key -> log.info("   {}:{}", key, arg0.get(key)));
    }

}
