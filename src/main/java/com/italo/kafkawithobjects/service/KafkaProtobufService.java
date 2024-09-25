package com.italo.kafkawithobjects.service;

import com.italo.kafkawithobjects.mapper.BorderoMapper;
import com.italo.kafkawithobjects.mapper.ParcelaMapper;
import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.italo.kafkawithobjects.resources.Constants.PROTO_TOPIC;

@Service
@RequiredArgsConstructor
public class KafkaProtobufService {

    private final KafkaTemplate<String, BorderoProto.Bordero> template;

    public ResponseEntity<String> sendMessage(Bordero request) {
        BorderoProto.Bordero message = BorderoMapper.INSTANCE.toBorderoProto(request);
        template.send(PROTO_TOPIC, message);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("MENSAGEM CRIADA COM SUCESSO E ENCAMINHADA AO KAFKA.");
    }
}
