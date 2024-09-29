package com.italo.kafkawithobjects.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

import static com.italo.kafkawithobjects.resources.Constants.JSON_TOPIC;
import static com.italo.kafkawithobjects.resources.Constants.PROTO_TOPIC;

// CLASSE APENAS PARA CRIAR O TOPICO NO KAFKA

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    public final KafkaProperties props;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        HashMap<String, Object> config = new HashMap<String, Object>();
        config.put("bootstrap.servers", props.getBootstrapServers());
        return new KafkaAdmin(config);
    }

    @Bean
    public KafkaAdmin.NewTopics newTopicJson() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(JSON_TOPIC)
                        .partitions(2)
                        .replicas(1)
                        .build());
    }

    @Bean
    public KafkaAdmin.NewTopics newTopicProto() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(PROTO_TOPIC)
                        .partitions(2)
                        .replicas(1)
                        .build());
    }
}
