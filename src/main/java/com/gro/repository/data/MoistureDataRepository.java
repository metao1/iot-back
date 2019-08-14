package com.gro.repository.data;

import com.gro.model.rpicomponent.AbstractRPiComponent;
import com.gro.model.rpicomponent.data.MoistureData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;


public interface MoistureDataRepository extends JpaRepository<MoistureData, Integer> {

    Page<MoistureData> findAllByComponent(AbstractRPiComponent component, Pageable pageable);

    @Query(
            value = "SELECT hd.id, CONVERT(DATE_FORMAT(hd.timestamp,'%Y-%m-00-00:00:00'),DATETIME) as 'timestamp', hd.component_id, ROUND(AVG(hd.moisture), 2) as `moisture` " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY MONTH(hd.timestamp) " +
                    "\n#pageable\n",

            countQuery = "SELECT COUNT(*) " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY MONTH(hd.timestamp), td.id",

            nativeQuery = true
    )
    Page<MoistureData> findMonthlyAverageByComponent(
        @Param("component") AbstractRPiComponent component, Pageable pageable);


    @Query(
            value = "SELECT hd.id, CONVERT(DATE_FORMAT(hd.timestamp,'%Y-%m-%d-00:00:00'),DATETIME) as 'timestamp', hd.component_id, ROUND(AVG(hd.moisture), 2) as `moisture` " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY DAY(hd.timestamp) ,td.id" +
                    "\n#pageable\n",

            countQuery = "SELECT COUNT(*) " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY DAY(hd.timestamp) ,td.id",

            nativeQuery = true
    )
    Page<MoistureData> findDailyAverageByComponent(
        @Param("component") AbstractRPiComponent component, Pageable pageable);


    @Query(
            value = "SELECT hd.id, CONVERT(DATE_FORMAT(hd.timestamp,'%Y-%m-%d-00:00:00'),DATETIME) as 'timestamp', hd.component_id, ROUND(MAX(hd.moisture), 2) as `moisture` " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY DAY(hd.timestamp) ,td.id" +
                    "\n#pageable\n",

            countQuery = "SELECT COUNT(*) " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY DAY(hd.timestamp) ,td.id",

            nativeQuery = true
    )
    Page<MoistureData> findDailyHighByComponent(
        @Param("component") AbstractRPiComponent component, Pageable pageable);


    @Query(
            value = "SELECT hd.id, CONVERT(DATE_FORMAT(hd.timestamp,'%Y-%m-%d-00:00:00'),DATETIME) as 'timestamp', hd.component_id, ROUND(MIN(hd.moisture), 2) as `moisture` " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY DAY(hd.timestamp) ,td.id" +
                    "\n#pageable\n",

            countQuery = "SELECT COUNT(*) " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY DAY(hd.timestamp) ,td.id",

            nativeQuery = true
    )
    Page<MoistureData> findDailyLowByComponent(
        @Param("component") AbstractRPiComponent component, Pageable pageable);


    @Query(
            value = "SELECT hd.id, CONVERT(DATE_FORMAT(hd.timestamp,'%Y-%m-%d-%H:00:00'),DATETIME) as 'timestamp', hd.component_id, ROUND(AVG(hd.moisture), 2) as `moisture` " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY HOUR(hd.timestamp), DAY(hd.timestamp) " +
                    "\n#pageable\n",

            countQuery = "SELECT COUNT(*) " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY HOUR(hd.timestamp), DAY(hd.timestamp) ,td.id",

            nativeQuery = true
    )
    Page<MoistureData> findHourlyAverageByComponent(
        @Param("component") AbstractRPiComponent component, Pageable pageable);


    @Query(
            value = "SELECT hd.id, CONVERT(DATE_FORMAT(hd.timestamp,'%Y-%m-%d-%H:00:00'),DATETIME) as 'timestamp', hd.component_id, ROUND(MAX(hd.moisture), 2) as `moisture` " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY HOUR(hd.timestamp), DAY(hd.timestamp) ,td.id" +
                    "\n#pageable\n",

            countQuery = "SELECT COUNT(*) " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY HOUR(hd.timestamp), DAY(hd.timestamp) ,td.id",

            nativeQuery = true
    )
    Page<MoistureData> findHourlyHighByComponent(
        @Param("component") AbstractRPiComponent component, Pageable pageable);


    @Query(
            value = "SELECT hd.id, CONVERT(DATE_FORMAT(hd.timestamp,'%Y-%m-%d-%H:00:00'),DATETIME) as 'timestamp', hd.component_id, ROUND(MIN(hd.moisture), 2) as `moisture` " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY HOUR(hd.timestamp), DAY(hd.timestamp) ,td.id" +
                    "\n#pageable\n",

            countQuery = "SELECT COUNT(*) " +
                    "FROM moisture_data hd " +
                    "WHERE hd.component_id = ?#{#component.id} " +
                    "GROUP BY HOUR(hd.timestamp), DAY(hd.timestamp) ,td.id",

            nativeQuery = true
    )
    Page<MoistureData> findHourlyLowByComponent(
        @Param("component") AbstractRPiComponent component, Pageable pageable);


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteByMoisture(Integer id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(MoistureData entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteByMoisture(Iterable<? extends MoistureData> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteInBatch(Iterable<MoistureData> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAllInBatch();

}
