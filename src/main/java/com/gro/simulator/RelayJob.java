package com.gro.simulator;

import com.gro.messaging.service.RelayEmitterService;
import com.gro.messaging.transformer.RelayMessageTransformer;
import com.gro.model.rpicomponent.data.RelayDTO;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
@Component
@DisallowConcurrentExecution
public class RelayJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelayJob.class);

    @Autowired
    RelayEmitterService humidityEmitterService;

    @Autowired
    RelayMessageTransformer relayMessageTransformer;

    @Autowired
    public RelayJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("relay is scheduled");
        Map<String, Object> headers = new HashMap<>();
        headers.put("relay", new Object());
        long timestamp = new Date().getTime();
        Message<String> message = MessageBuilder.createMessage("{\"state\":\"ON\"" + ", \"componentId\":5,\"timestamp\":" + timestamp + "}", new MessageHeaders(headers));
        LOGGER.info("relay is {}", message.getPayload());
        try {
            Message<RelayDTO> transform = relayMessageTransformer.transform(message);
            humidityEmitterService.process(transform);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
