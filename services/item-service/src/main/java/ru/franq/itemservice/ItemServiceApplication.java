package ru.franq.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import ru.franq.itemservice.redis.RedisConfig;

@SpringBootApplication
@EnableKafka
@EnableCaching
@EnableConfigurationProperties(RedisConfig.class)
@EnableJpaRepositories(basePackages = "ru.franq.itemservice.persistence.repos")
@EnableRedisRepositories(basePackages = "ru.franq.itemservice.redis.repo")
public class ItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

}
