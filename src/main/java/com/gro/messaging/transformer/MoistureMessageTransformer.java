package com.gro.messaging.transformer;

import com.gro.model.MoistureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@MessageEndpoint
public class MoistureMessageTransformer {

    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Transformer(inputChannel = "moistureTransformerChannel",
            outputChannel = "moistureServiceChannel")
    public Message<MoistureDTO> transform(Message<String> message) throws Exception {
        String payload = message.getPayload();
        MoistureDTO data = jackson2JsonObjectMapper.fromJson(payload, MoistureDTO.class);
        return MessageBuilder.createMessage(data, message.getHeaders());
    }

}
