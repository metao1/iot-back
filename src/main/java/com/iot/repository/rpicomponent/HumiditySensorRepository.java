package com.iot.repository.rpicomponent;

import com.iot.model.rpicomponent.component.HumiditySensor;
import com.iot.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumiditySensorRepository extends RestrictedWriteRepository<HumiditySensor, Integer> {

}
