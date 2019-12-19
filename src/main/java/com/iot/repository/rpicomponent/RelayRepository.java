package com.iot.repository.rpicomponent;

import com.iot.model.rpicomponent.component.Relay;
import com.iot.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelayRepository extends RestrictedWriteRepository<Relay, Integer> {

    @Override
    Optional<Relay> findById(Integer integer);
}
