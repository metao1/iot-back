package com.gro.repository.rpicomponent;

import com.gro.model.rpipin.RPiPin;
import com.gro.repository.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RPiPinRepository extends ReadOnlyRepository<RPiPin, Integer> {

    Optional<RPiPin> findByPhysicalPin(int physicalPin);
}
