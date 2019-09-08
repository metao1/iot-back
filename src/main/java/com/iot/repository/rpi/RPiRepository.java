package com.iot.repository.rpi;

import com.iot.model.rpi.RPi;
import com.iot.repository.RestrictedWriteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPiRepository extends RestrictedWriteRepository<RPi, Integer> {

}
