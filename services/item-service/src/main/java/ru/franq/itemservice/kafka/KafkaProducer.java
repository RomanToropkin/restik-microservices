package ru.franq.itemservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.franq.itemservice.data.event.Event;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void sendEvent(String topic, Event event){
        log.info("Send to topic %s: %s".formatted(topic, event));
        kafkaTemplate.send(topic, event);
    }

}
