package com.italo.kafkawithobjects.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class JsonConsumerConfig {

    private final KafkaProperties props;

    @Bean
    public ConsumerFactory<String, Object> jsonConsumerFactory() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", props.getBootstrapServers());
        config.put("key.deserializer", StringDeserializer.class);
        config.put("value.deserializer", StringDeserializer.class);
        config.put("auto.offset.reset", "earliest"); // INICIAR PROCESSAMENTO DO PRIMEIRO OFFSET
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> jsonListenerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String,Object>();
        factory.setConsumerFactory(jsonConsumerFactory());
        factory.setRecordMessageConverter(new JsonMessageConverter()); // Todas as mensagens serão convertidas em JSON
        return factory;
    }



}
