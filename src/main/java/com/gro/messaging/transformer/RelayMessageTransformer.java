package com.gro.messaging.transformer;

import com.gro.model.rpicomponent.data.RelayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

//@MessageEndpoint
@Component
public class RelayMessageTransformer {


    /*@Transformer(inputChannel = "relayTransformerChannel",
            outputChannel = "relayEmitterChannel")*/
    public Message<RelayDTO> transform(RelayDTO messageActual, Message<String> message) throws Exception {
        return MessageBuilder.createMessage(messageActual, message.getHeaders());
    }

}
