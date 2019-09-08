package com.iot.repository.rpicomponent;

import com.iot.model.rpipin.RPiPin;
import com.iot.repository.ReadOnlyRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RPiPinRepository extends ReadOnlyRepository<RPiPin, Integer> {

    Optional<RPiPin> findByPhysicalPin(int physicalPin);
}
