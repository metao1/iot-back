package com.iot.repository.rpicomponent;

import com.iot.model.rpicomponent.component.ProximitySensor;
import com.iot.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProximitySensorRepository extends RestrictedWriteRepository<ProximitySensor, Integer> {

}
