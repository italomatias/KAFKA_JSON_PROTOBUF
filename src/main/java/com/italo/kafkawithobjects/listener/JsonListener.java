package com.italo.kafkawithobjects.listener;

import com.italo.kafkawithobjects.model.Bordero;
import lombok.extern.log4j.Log4j2;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JsonListener {

    @KafkaListener(topics = "Json-Topic", groupId = "Json-Consumer", containerFactory = "jsonListenerFactory")
    public void listenJson(Bordero msg) throws Exception { //Objeto que será feito o maper do JSON CONVERTER
     /* SE NAO FOR INFORMADO O OBJETO PODE REMOVER o setRecordMessageConverter das
        propiedades do consumidor e receber o objeto direto como uma string e fazer
        o maper ou conversão na mão */

        log.info("MESSAGE RECIEVED {}", msg);
        // IMPLEMENTAR SERVICE PARA PROCESSAR MSG CONFORME NECESSIDADE

        //throw new NullPointerException("ERRO TESTE REPROCESSAR");
        throw new Exception("ERRO TESTE REPROCESSAR");

    }
}
