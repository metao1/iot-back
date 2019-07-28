package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.component.MoistureSensor;
import com.gro.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoistureSensorRepository extends RestrictedWriteRepository<MoistureSensor, Integer> {

}
