package com.iot.repository.rpicomponent;

import com.iot.model.rpicomponent.component.MoistureSensor;
import com.iot.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoistureSensorRepository extends RestrictedWriteRepository<MoistureSensor, Integer> {

}
