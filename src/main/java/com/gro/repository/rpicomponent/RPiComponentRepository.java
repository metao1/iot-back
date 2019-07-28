package com.gro.repository.rpicomponent;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.RPiComponentType;
import com.gro.repository.RestrictedWriteRepository;

import java.util.List;

public interface RPiComponentRepository extends RestrictedWriteRepository<AbstractRPiComponent, Integer> {

    AbstractRPiComponent findById(Integer id);

    List<AbstractRPiComponent> findAllByType(RPiComponentType componentType);
}
