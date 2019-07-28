package com.gro.repository;

import com.gro.model.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends BaseRepository<Notification, Integer> {

    @Query("SELECT n FROM Notification n WHERE isRead = :read")
    public Page<Notification> findAllByIsRead(@Param("read") Boolean read, Pageable pageable);

}
