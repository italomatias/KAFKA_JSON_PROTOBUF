package com.italo.kafkawithobjects.config;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class JsonConsumerConfig {

    @Value(value = "${jsonconsumer.interval.retry}")
    private Long interval;

    @Value(value = "${jsonconsumer.max.retries}")
    private Long maxAttempts;

    private final KafkaProperties props;

    @Bean
    public ConsumerFactory<String, Object> jsonConsumerFactory() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", props.getBootstrapServers());
        config.put("key.deserializer", StringDeserializer.class);
        config.put("value.deserializer", StringDeserializer.class);
        config.put("auto.offset.reset", "earliest"); // INICIAR PROCESSAMENTO DO PRIMEIRO OFFSET
        config.put("AckMode", ContainerProperties.AckMode.RECORD); //FORÇA A NOTIFICAÇÃO DO CONSUMO POR CADA MSG PROCESSADA
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> jsonListenerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String,Object>();
        factory.setConsumerFactory(jsonConsumerFactory());
        factory.setRecordMessageConverter(new JsonMessageConverter()); // TODAS AS MSG SERAO CONVERTIDAS EM JSON
        factory.setCommonErrorHandler(jsonConsumerErrorHandler()); // DEFINE HANDLER QUE VAI TRATAR OS ERROS DESSE LISTENER
        return factory;
    }

    @Bean
    public DefaultErrorHandler jsonConsumerErrorHandler() {

        // DEFINE O TEMPO EM MS ANTES DE TENTAR REPROCESSAR A MSG E A QTD DE VEZES
        BackOff fixedBackOff = new FixedBackOff(interval, maxAttempts);
        // ATENÇÃO A LEITURA DO TOPICO FICA PARADA ATÉ A CONCLUSÃO DA LEITURA DESSA MSG

        // EXECCUTA QUANDO CHEGA AO LIMITE MÁXIMO DE TENTATIVAS DEFINIDAS NO BAKOFF
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {

            log.error("ERRO MAX ATTEMPTS: {} " , maxAttempts);
            log.error("MESSAGE : {} " , consumerRecord.value());
            log.error("EXCEPTION MESSAGE: {} " , exception.getMessage());
            log.error("EXCEPTION STACK {}" , Arrays.toString(exception.getStackTrace()));

            // INSIRA SUA TRATATIVA DE MENSGEM NAO CONSUMIDA EX : DLQ , NOTIFICACAO...

        }, fixedBackOff);

        // DEFINE QUAIS TIPOS DE EXCECAO SERAO TRATADAS NO RETRY EX EXCEPTIONS CUSTOMIZADAS
        errorHandler.addRetryableExceptions(SocketTimeoutException.class);

        // DEFINE EXCECOES NAO TRATADAS NO RETRI E VAO DIRETO PRA O PROCESSAMENTO DO MAX RETRIES
        errorHandler.addNotRetryableExceptions(NullPointerException.class);

        return errorHandler;
    }
}
