package com.italo.kafkawithobjects.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.Serializable;
import java.util.HashMap;

// PRODUCER COM SUAS CONFIGS DE CONEXAO
@Configuration
@RequiredArgsConstructor
public class JsonProducerConfig {

    private final KafkaProperties props;

    @Bean
    public ProducerFactory jsonProducerFactory() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", props.getBootstrapServers());
        config.put("Key.serializer", StringSerializer.class);
        config.put("Value.serializer", JsonSerializer.class);
        config.put("delivery.timeout.ms",30000 ); // REDUZIDO TIMEOUT AO MANDAR MSGS PARA FACILITAR TESTES
        config.put("default.api.timeout.ms",30000); // REDUZIDO TIMEOUT AO TENTAR CONECTAR NO TOPICO PARA FACILITAR TESTES

        return new DefaultKafkaProducerFactory(
                config,
                new StringSerializer(),
                new JsonSerializer());
    }

    @Bean
    public KafkaTemplate<String, Serializable> jsonKafkaTemplate() {
        return new KafkaTemplate<>(jsonProducerFactory());
    }

}
