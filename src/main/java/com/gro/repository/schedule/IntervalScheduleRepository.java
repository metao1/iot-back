package com.gro.repository.schedule;

import com.gro.scheduling.model.IntervalScheduleJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalScheduleRepository extends CrudRepository<IntervalScheduleJob, Integer> {
    List<IntervalScheduleJob> findAll();
}
