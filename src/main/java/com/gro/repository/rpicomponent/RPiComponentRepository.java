package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.RPiComponentType;
import com.gro.repository.RestrictedWriteRepository;

import java.util.List;
import java.util.Optional;

public interface RPiComponentRepository extends RestrictedWriteRepository<AbstractRPiComponent, Integer> {

    Optional<AbstractRPiComponent> findById(Integer id);

    List<AbstractRPiComponent> findAllByType(RPiComponentType componentType);
}
