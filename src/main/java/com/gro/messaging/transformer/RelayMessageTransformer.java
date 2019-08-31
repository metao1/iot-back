package com.gro.messaging.transformer;

import com.gro.model.rpicomponent.data.RelayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@MessageEndpoint
public class RelayMessageTransformer {

    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Transformer(inputChannel = "relayTransformerChannel",
            outputChannel = "relayEmitterChannel")
    public Message<RelayDTO> transform(Message<String> message) throws Exception {
        RelayDTO relayDTO = jackson2JsonObjectMapper.fromJson(message.getPayload(),message.getHeaders());
        return MessageBuilder.createMessage(relayDTO, message.getHeaders());
    }

}
