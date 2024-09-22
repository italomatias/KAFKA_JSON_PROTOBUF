package com.italo.kafkawithobjects.service;

import com.italo.kafkawithobjects.mapper.ProtoMapper;
import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;

import static com.italo.kafkawithobjects.resources.Constants.PROTO_TOPIC;

@Service
@RequiredArgsConstructor
public class KafkaProtobufService {

    private final KafkaTemplate<String, BorderoProto.Bordero> template;

    public ResponseEntity<String> sendMessage(Bordero request){
        // CONVERTER JSON PARA PROTO USANDO MAPPER
        var teste = ProtoMapper.INSTANCE.toBorderoProto(request);
       /*var teste = BorderoProto.Bordero.newBuilder()
               .setNumbordero("555")
               .setDataop("01/08/2028")
               .setValor(Float.parseFloat("66.99"))
                //.setTipoPessoa(BorderoProto.TipoPessoa.FISICA)
                /*.addParcela(BorderoProto.Parcela.newBuilder()
                        .setParcela(1)
                        .setValor(Float.parseFloat("66.99"))
                        .setDataVencimento("26/08/2027")
                        .build())
                .build();*/

        template.send(PROTO_TOPIC,teste);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("MENSAGEM CRIADA COM SUCESSO E ENCAMINHADA AO KAFKA.");
    }
}
