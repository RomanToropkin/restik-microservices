package ru.franq.itemservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.franq.itemservice.data.event.Event;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Event> kafkaTemplate;
    private ObjectWriter writer;
    @PostConstruct
    void init(){
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        writer = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @Value("${item-service.topic}")
    private String topic;

    @SneakyThrows
    public void sendEvent(String eventType, Object msg){
        var event = new Event(eventType, msg);
        log.info("Send to topic %s: %s".formatted(topic, writer.writeValueAsString(event)));
        kafkaTemplate.send(topic, event);
    }

}
