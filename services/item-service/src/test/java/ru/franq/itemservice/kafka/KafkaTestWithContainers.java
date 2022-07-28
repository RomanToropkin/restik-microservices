package ru.franq.itemservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.franq.itemservice.persistence.entity.Item;

@SpringBootTest()
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@DirtiesContext
@Import({TestKafkaConfig.class})
@Slf4j
class KafkaTestWithContainers {

    private static final String TEST_TOPIC = "test-topic";
    @Autowired
    private KafkaProducer producer;
    @Autowired
    Consumer<Integer, String> consumer;

    @Test
    void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
            throws Exception {
        var item = Item.builder()
                .id("id")
                .title("Test")
                .description("Test 2")
                .calories(100)
                .protein(1)
                .fat(2)
                .carbohydrates(3)
                .build();

        producer.sendEvent("test-event", item);

        ConsumerRecord<Integer, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, TEST_TOPIC);
        log.info("Get data: "+singleRecord.value());

    }

}
