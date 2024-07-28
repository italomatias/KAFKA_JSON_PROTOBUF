package com.italo.kafkawithobjects.resources;

import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.protobuf.BorderoProto;
import com.italo.kafkawithobjects.service.KafkaJsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class Rest {

    private final KafkaJsonService kafkaJsonService;

    @PostMapping("/json")
    public ResponseEntity<String> json (@RequestBody Bordero request){
        return kafkaJsonService.sendMessage(request);
    }

    @GetMapping(value="/protoget", produces = "application/x-protobuf")
    public BorderoProto.Bordero protoget (){
        return BorderoProto.Bordero.newBuilder()
                .setId(1)
                .setNumBordero("999999")
                .setDataOp("28/07/2024")
                .setTipoPessoa(BorderoProto.TipoPessoa.FISICA)
                .build();
    }
}
