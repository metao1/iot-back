package com.gro.repository.schedule;

import com.gro.scheduling.model.AbstractScheduleJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<AbstractScheduleJob, Integer> {
    List<AbstractScheduleJob> findAll();
}
