package com.gro.messaging.service;

import com.gro.model.MoistureDTO;
import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.component.HumiditySensor;
import com.gro.model.rpicomponent.component.MoistureSensor;
import com.gro.model.rpicomponent.data.HumidityData;
import com.gro.model.rpicomponent.data.MoistureData;
import com.gro.repository.data.MoistureDataRepository;
import com.gro.repository.rpicomponent.RPiComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoisturePersistenceService {

    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @Autowired
    private MoistureDataRepository moistureDataRepository;

    @ServiceActivator(inputChannel = "humidityServiceChannel")
    public void process(Message<MoistureDTO> message) {
        MoistureDTO payload = message.getPayload();
        Optional<AbstractRPiComponent> component = rPiComponentRepository.findById(payload.getComponentId());
        if (component.isPresent()) {
            MoistureData data = new MoistureData();
            data.setMoisture(payload.getMoisture());
            data.setTimestamp(payload.getTimestamp());
            data.setComponent((MoistureSensor) component.get());
            moistureDataRepository.save(data);
        }
    }

}
