package com.ecommerce.notificationservice.repository;

import com.ecommerce.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface INotificationRepository extends JpaRepository<Notification, String>, JpaSpecificationExecutor<Notification> {
}
