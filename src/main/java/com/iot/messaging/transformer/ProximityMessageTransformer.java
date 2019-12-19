package com.iot.messaging.transformer;

import com.iot.model.rpicomponent.data.ProximityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@MessageEndpoint
public class ProximityMessageTransformer {

    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Transformer(inputChannel = "proximityTransformerChannel",
            outputChannel = "proximityServiceChannel")
    public Message<ProximityDTO> transform(Message<String> message) throws Exception {
        String payload = message.getPayload();
        ProximityDTO data = jackson2JsonObjectMapper.fromJson(payload, ProximityDTO.class);
        return MessageBuilder.createMessage(data, message.getHeaders());
    }

}
