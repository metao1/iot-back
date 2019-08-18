package com.gro.simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gro.handler.ObjectFactory;
import com.gro.messaging.service.TemperatureEmitterService;
import com.gro.messaging.service.TemperaturePersistenceService;
import com.gro.messaging.transformer.TemperatureMessageTransformer;
import com.gro.model.rpicomponent.component.TemperatureSensor;
import com.gro.model.rpicomponent.data.TemperatureDTO;
import com.gro.model.rpicomponent.data.TemperatureData;
import com.gro.repository.rpicomponent.TemperatureSensorRepository;
import com.gro.utils.WebUtils;
import io.netty.util.internal.StringUtil;
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
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
@DisallowConcurrentExecution
public class TemperatureJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureJob.class);

    @Autowired
    TemperatureEmitterService temperatureEmitterService;

    @Autowired
    TemperatureMessageTransformer temperatureMessageTransformer;

    @Autowired
    TemperaturePersistenceService temperaturePersistenceService;

    @Autowired
    TemperatureSensorRepository temperatureSensorRepository;

    @Autowired
    ObjectFactory objectFactory;

    @Autowired
    public TemperatureJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("temperature is scheduled");
        Map<String, Object> headers = new HashMap<>();
        headers.put("temperature", new Object());
        Iterable<TemperatureSensor> temperatureSensors = temperatureSensorRepository.findAll();
        for (TemperatureSensor temperatureSensor : temperatureSensors) {
            double temperature = BigDecimal.valueOf(
                ThreadLocalRandom.current().nextDouble(25, 30)
            ).setScale(2, RoundingMode.CEILING).doubleValue();
            TemperatureDTO temperatureDTO = new TemperatureDTO();
            temperatureDTO.setTemperature(temperature);
            temperatureDTO.setComponentId(temperatureSensor.getId());
            temperatureDTO.setTimestamp(new Date());
            String temperaturePayload = objectFactory.toJson(new TypeReference<TemperatureDTO>() {
            }, temperatureDTO);
            if (StringUtil.isNullOrEmpty(temperaturePayload)) {
                return;
            }
            Message<String> message = MessageBuilder.createMessage(temperaturePayload, new MessageHeaders(headers));
            LOGGER.info("temperature is {}", temperature);
            try {
                Message<TemperatureDTO> transform = temperatureMessageTransformer.transform(message);
                temperatureEmitterService.process(transform);
                temperaturePersistenceService.process(transform);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}