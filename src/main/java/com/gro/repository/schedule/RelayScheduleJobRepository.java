package com.gro.repository.schedule;

import com.gro.model.relay.RelayScheduleJob;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PreAuthorize("hasRole('ADMIN')")
public interface RelayScheduleJobRepository extends CrudRepository<RelayScheduleJob, Integer> {
    List<RelayScheduleJob> findAll();

    List<RelayScheduleJob> findByEnabled(Boolean enabled);

    List<RelayScheduleJob> findAllByComponentId(Integer id);
}
