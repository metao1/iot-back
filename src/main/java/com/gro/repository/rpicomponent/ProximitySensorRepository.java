package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.component.ProximitySensor;
import com.gro.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProximitySensorRepository extends RestrictedWriteRepository<ProximitySensor, Integer> {

}
