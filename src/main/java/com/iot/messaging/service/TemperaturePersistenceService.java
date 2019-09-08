package com.iot.messaging.service;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.component.TemperatureSensor;
import com.iot.model.rpicomponent.data.TemperatureDTO;
import com.iot.model.rpicomponent.data.TemperatureData;
import com.iot.repository.data.TemperatureDataRepository;
import com.iot.repository.rpicomponent.RPiComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.Optional;

@MessageEndpoint
public class TemperaturePersistenceService {

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    @ServiceActivator(inputChannel = "temperatureServiceChannel")
    public void process(Message<TemperatureDTO> message) {

        TemperatureDTO payload = message.getPayload();
        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(payload.getComponentId());
        if (component.isPresent()) {
            TemperatureData data = new TemperatureData();
            data.setTemperature(payload.getTemperature());
            data.setTimestamp(payload.getTimestamp());
            data.setComponent((TemperatureSensor) component.get());
            temperatureDataRepository.save(data);
        }
    }

}
