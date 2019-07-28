package com.gro.messaging.service;

import com.gro.model.rpicomponent.data.HumidityDTO;
import com.gro.repository.data.HumidityDataRepository;
import com.gro.repository.rpicomponent.RPiComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class HumidityPersistenceService {
/*
    @Autowired
    private RPiComponentRepository rPiComponentRepository;

    @Autowired
    private HumidityDataRepository humidityDataRepository;*/

    @ServiceActivator(inputChannel = "humidityServiceChannel")
    public void process(Message<HumidityDTO> message) {

   /*     HumidityDTO payload = message.getPayload();
        AbstractRPiComponent component = rPiComponentRepository.findById(payload.getId());
        if(component != null) {
            HumidityData data = new HumidityData();
            data.setHumidity(payload.getHumidity());
            data.setTimestamp(payload.getTimestamp());
            data.setComponent(component);
            humidityDataRepository.save(data);
        }*/
    }

}
