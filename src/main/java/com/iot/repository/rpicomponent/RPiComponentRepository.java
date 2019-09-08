package com.iot.repository.rpicomponent;

import com.iot.model.rpicomponent.AbstractRPiComponent;
import com.iot.model.rpicomponent.RPiComponentType;
import com.iot.repository.RestrictedWriteRepository;

import java.util.List;
import java.util.Optional;

public interface RPiComponentRepository extends RestrictedWriteRepository<AbstractRPiComponent, Integer> {

    Optional<AbstractRPiComponent> findById(Long id);

    List<AbstractRPiComponent> findAllByType(RPiComponentType componentType);
}
