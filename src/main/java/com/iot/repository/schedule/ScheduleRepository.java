package com.iot.repository.schedule;

import com.iot.scheduling.model.AbstractScheduleJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<AbstractScheduleJob, Integer> {
    List<AbstractScheduleJob> findAll();
}
