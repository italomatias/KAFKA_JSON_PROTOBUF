package com.italo.kafkawithobjects.listener;

import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import com.italo.kafkawithobjects.mapper.BorderoMapper;
import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.converter.ProtobufMessageConverter;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ProtoListener {

    @KafkaListener(topics = "ProtBuff-Topic", groupId = "Proto-Consumer", containerFactory = "protoListenerFactory")
    public void listenProto(BorderoProto.Bordero msg) throws Exception { //Objeto que será feito o maper do JSON CONVERTER
        log.info("MESSAGE PROTO RECIEVED {}", msg);
        // CONVERSAO PARA OBJETO DTO
        Bordero Objeto = BorderoMapper.INSTANCE.toBordero(msg);
    }
}
