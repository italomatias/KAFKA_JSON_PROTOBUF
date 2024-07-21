package com.italo.kafkawithobjects.resources;

import com.italo.kafkawithobjects.model.Bordero;
import com.italo.kafkawithobjects.service.KafkaJsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class Rest {

    private final KafkaJsonService kafkaJsonService;

    @PostMapping("/json")
    public ResponseEntity<String> json (@RequestBody Bordero request){
        return kafkaJsonService.sendMessage(request);
    }
}
