package com.gro.simulator;

import com.gro.messaging.service.HumidityEmitterService;
import com.gro.messaging.transformer.HumidityMessageTransformer;
import com.gro.model.rpicomponent.data.HumidityDTO;
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
public class HumidityJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(HumidityJob.class);

    private Random random = new Random();

    @Autowired
    HumidityEmitterService humidityEmitterService;

    @Autowired
    HumidityMessageTransformer humidityMessageTransformer;

    @Autowired
    public HumidityJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("humidity is ");
        Map<String, Object> headers = new HashMap<>();
        headers.put("humidity", new Object());
        double humidity =
                BigDecimal.valueOf(
                        ThreadLocalRandom.current().nextDouble(0, 100)
                ).setScale(2, RoundingMode.CEILING).doubleValue();
        long timestamp = new Date().getTime();
        Message<String> message = MessageBuilder.createMessage("{\"humidity\":" + humidity + ", \"componentId\":10,\"timestamp\":" + timestamp + "}", new MessageHeaders(headers));
        try {
            Message<HumidityDTO> transform = humidityMessageTransformer.transform(message);
            humidityEmitterService.process(transform);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}