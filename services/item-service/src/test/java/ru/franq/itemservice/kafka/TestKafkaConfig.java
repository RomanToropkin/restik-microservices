package ru.franq.itemservice.kafka;

import com.fasterxml.jackson.databind.JsonSerializable;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Collections;
import java.util.Map;

@TestConfiguration
public class TestKafkaConfig {

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Bean
    public Consumer<Integer, String> consumer(){
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("item-service", "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_DOC, JsonSerializer.class);
        Consumer<Integer, String> consumer = new DefaultKafkaConsumerFactory<Integer, String>(consumerProps)
                .createConsumer();
        consumer.subscribe(Collections.singleton("test-topic"));
        return consumer;
    }

}
