package com.gro.messaging.service;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.component.TemperatureSensor;
import com.gro.model.rpicomponent.data.TemperatureDTO;
import com.gro.model.rpicomponent.data.TemperatureData;
import com.gro.repository.data.TemperatureDataRepository;
import com.gro.repository.rpicomponent.RPiComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
