package com.iot.repository.schedule;

import com.iot.scheduling.model.FixedTimeScheduleJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedTimeScheduleRepository extends CrudRepository<FixedTimeScheduleJob, Integer> {
    List<FixedTimeScheduleJob> findAll();
}
