package com.iot.repository.data;

import com.iot.model.rpicomponent.data.ProximityData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProximityDataRepository extends JpaRepository<ProximityData, Integer> {

}
