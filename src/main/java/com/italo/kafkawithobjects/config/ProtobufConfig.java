package com.italo.kafkawithobjects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@Configuration
public class ProtobufConfig {

    // SPRING NAO CONSEGUE CONVERTER MSG PROTO PARA RETORNO , LOGO É NECESSÁRIO ESSE BEAN
    // PARA CONVERTER OS PROTO PARA RETORNO HTTP !
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {return new ProtobufHttpMessageConverter();
    }
}
