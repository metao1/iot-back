package com.gro.messaging.transformer;

import com.gro.model.rpicomponent.data.HumidityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class HumidityMessageTransformer {

    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Transformer(inputChannel = "humidityTransformerChannel",
            outputChannel = "humidityServiceChannel")
    public Message<HumidityDTO> transform(Message<String> message) throws Exception {
        String payload = message.getPayload();
        HumidityDTO data = jackson2JsonObjectMapper.fromJson(payload, HumidityDTO.class);
        return MessageBuilder.createMessage(data, message.getHeaders());
    }

}
