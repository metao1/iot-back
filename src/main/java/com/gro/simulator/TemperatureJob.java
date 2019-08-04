package com.gro.simulator;

import com.gro.messaging.service.TemperatureEmitterService;
import com.gro.messaging.transformer.TemperatureMessageTransformer;
import com.gro.model.rpicomponent.data.TemperatureDTO;
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
import java.util.concurrent.ThreadLocalRandom;

@Component
@DisallowConcurrentExecution
public class TemperatureJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureJob.class);

    @Autowired
    TemperatureEmitterService humidityEmitterService;

    @Autowired
    TemperatureMessageTransformer humidityMessageTransformer;

    @Autowired
    public TemperatureJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("temperature is scheduled");
        Map<String, Object> headers = new HashMap<>();
        headers.put("temperature", new Object());
        long timestamp = new Date().getTime();
        double temperature = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(25, 30)
        ).setScale(2, RoundingMode.CEILING).doubleValue();
        Message<String> message = MessageBuilder.createMessage("{\"temperature\":" + temperature + ", \"componentId\":3,\"timestamp\":" + timestamp + "}", new MessageHeaders(headers));
        try {
            Message<TemperatureDTO> transform = humidityMessageTransformer.transform(message);
            humidityEmitterService.process(transform);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}