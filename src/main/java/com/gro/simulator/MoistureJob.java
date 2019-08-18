package com.gro.simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gro.handler.ObjectFactory;
import com.gro.messaging.service.MoistureEmitterService;
import com.gro.messaging.service.MoisturePersistenceService;
import com.gro.messaging.transformer.MoistureMessageTransformer;
import com.gro.model.MoistureDTO;
import com.gro.model.rpicomponent.component.MoistureSensor;
import com.gro.repository.rpicomponent.MoistureSensorRepository;
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
    MoisturePersistenceService moisturePersistenceService;

    @Autowired
    MoistureSensorRepository moistureSensorRepository;

    @Autowired
    ObjectFactory objectFactory;

    @Autowired
    public MoistureJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, Object> headers = new HashMap<>();
        headers.put("moisture", new Object());
        Iterable<MoistureSensor> allMoistureSensors = moistureSensorRepository.findAll();
        for (MoistureSensor moistureSensor : allMoistureSensors) {
            double moisture =
                BigDecimal.valueOf(
                    ThreadLocalRandom.current().nextDouble(0, 100)
                ).setScale(2, RoundingMode.CEILING).doubleValue();
            MoistureDTO moistureDTO = new MoistureDTO();
            moistureDTO.setMoisture(moisture);
            moistureDTO.setComponentId(moistureSensor.getId());
            moistureDTO.setTimestamp(new Date());
            String moisturePayload = objectFactory.toJson(new TypeReference<MoistureDTO>() {
            }, moistureDTO);
            Message<String> message = MessageBuilder.createMessage(moisturePayload, new MessageHeaders(headers));
            LOGGER.info("moisture is {}", moisture);
            try {
                Message<MoistureDTO> transform = moistureMessageTransformer.transform(message);
                moistureEmitterService.process(transform);
                moisturePersistenceService.process(transform);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}