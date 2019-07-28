package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.component.TemperatureSensor;
import com.gro.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureSensorRepository extends RestrictedWriteRepository<TemperatureSensor, Integer> {

}
