package com.iot.repository.rpicomponent;

import com.iot.model.rpicomponent.component.TemperatureSensor;
import com.iot.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureSensorRepository extends RestrictedWriteRepository<TemperatureSensor, Integer> {

}
