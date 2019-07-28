package com.gro.repository.rpi;

import com.gro.model.rpi.RPi;
import com.gro.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPiRepository extends RestrictedWriteRepository<RPi, Integer> {

}
