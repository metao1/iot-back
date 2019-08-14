package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.component.Relay;
import com.gro.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelayRepository extends RestrictedWriteRepository<Relay, Integer> {

    @Override
    Optional<Relay> findById(Integer integer);
}
