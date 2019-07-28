package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.component.Relay;
import com.gro.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayRepository extends RestrictedWriteRepository<Relay, Integer> {

}
