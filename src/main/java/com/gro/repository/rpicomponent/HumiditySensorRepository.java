package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.component.HumiditySensor;
import com.gro.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumiditySensorRepository extends RestrictedWriteRepository<HumiditySensor, Integer> {

}
