package com.italo.kafkawithobjects.config;

import com.italo.kafkawithobjects.protobuf.BorderoProto;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.Serializable;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class ProtoProducerConfig {

    private final KafkaProperties props;

    @Bean
    ProducerFactory protoProducerFactory() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", props.getBootstrapServers());
        config.put("Key.serializer", StringSerializer.class);
        config.put("Value.serializer", KafkaProtobufSerializer.class);
        config.put("delivery.timeout.ms",30000 ); // REDUZIDO TIMEOUT AO MANDAR MSGS PARA FACILITAR TESTES
        config.put("default.api.timeout.ms",30000); // REDUZIDO TIMEOUT AO TENTAR CONECTAR NO TOPICO PARA FACILITAR TESTES
        config.put("schema.registry.url","http://127.0.0.1:8081"); // NAO PODE COLOCAR LOCALHOST
        config.put("auto.register.schemas", true);

        return new DefaultKafkaProducerFactory(
                config,
                new StringSerializer(),
                new KafkaProtobufSerializer());
    }

    @Bean
    public KafkaTemplate<String, BorderoProto.Bordero> protobufKafkaTemplate() {
        return new KafkaTemplate<>(protoProducerFactory());
    }
}
