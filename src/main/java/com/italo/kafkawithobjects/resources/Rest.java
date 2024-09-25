package com.italo.kafkawithobjects.resources;

import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.service.KafkaJsonService;
import com.italo.kafkawithobjects.service.KafkaProtobufService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class Rest {

    private final KafkaJsonService kafkaJsonService;
    private final KafkaProtobufService kafkaProtobufService;

    @PostMapping("/json")
    public ResponseEntity<String> json (@RequestBody Bordero request){
        return kafkaJsonService.sendMessage(request);
    }

    @PostMapping("/protobuf")
    public ResponseEntity<String> protobuf(@RequestBody Bordero request){
        return kafkaProtobufService.sendMessage(request);
    }
}
