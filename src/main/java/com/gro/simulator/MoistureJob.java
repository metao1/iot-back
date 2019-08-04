package com.gro.simulator;

import com.gro.messaging.service.MoistureEmitterService;
import com.gro.messaging.transformer.MoistureMessageTransformer;
import com.gro.model.MoistureDTO;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@DisallowConcurrentExecution
public class MoistureJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoistureJob.class);

    Random random = new Random();

    @Autowired
    MoistureEmitterService moistureEmitterService;

    @Autowired
    MoistureMessageTransformer moistureMessageTransformer;

    @Autowired
    public MoistureJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, Object> headers = new HashMap<>();
        headers.put("moisture", new Object());
        long timestamp = new Date().getTime();
        double moisture =
                BigDecimal.valueOf(
                        ThreadLocalRandom.current().nextDouble(0, 100)
                ).setScale(2, RoundingMode.CEILING).doubleValue();
        Message<String> message = MessageBuilder.createMessage("{\"moisture\":" + moisture + ", \"componentId\":2,\"timestamp\":" + timestamp + "}", new MessageHeaders(headers));
        try {
            Message<MoistureDTO> transform = moistureMessageTransformer.transform(message);
            moistureEmitterService.process(transform);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}