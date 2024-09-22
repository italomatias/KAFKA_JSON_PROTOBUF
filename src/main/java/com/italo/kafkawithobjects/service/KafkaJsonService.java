package com.italo.kafkawithobjects.service;

import com.italo.kafkawithobjects.model.Bordero;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.io.Serializable;


import static com.italo.kafkawithobjects.resources.Constants.JSON_TOPIC;

@Service
@RequiredArgsConstructor
@Log4j2
public class KafkaJsonService {

    private final KafkaTemplate<String, Serializable> template;

    public ResponseEntity<String> sendMessage(Bordero bordero) {

        try {
            template.send(JSON_TOPIC, bordero).whenComplete( // PROCESSO ASSINCRONO !
                    (result, exception) -> {
                        if (exception != null) {
                            log.error("!!!!! ERRO AO ENVIAR MSG !!!!!", exception);
                            // FAZER TRATATIVA DE ERRO NA NOVA THREAD PARA GARANTIR O ENVIO DA MENSAGEM OU TRATATIVA DE ERROS
                        } else {
                            log.info("MENSAGEM ESCRITA NO: TOPICO: {} PARTITION: {} OFFSET: {} ",
                                    result.getRecordMetadata().topic(),
                                    result.getRecordMetadata().partition(),
                                    result.getRecordMetadata().offset());
                        }
                    }
            );
        }
        catch (Exception e) {
            log.error("ERRO AO ENVAR MENSAGEM.",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause().getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("MENSAGEM CRIADA COM SUCESSO E ENCAMINHADA AO KAFKA.");
    }
}
