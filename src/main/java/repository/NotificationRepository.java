package com.rev.app.revhire.repository;

import com.rev.app.revhire.entity.Notification;
import com.rev.app.revhire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);

    List<Notification> findByUserAndIsReadOrderByCreatedAtDesc(User user, boolean isRead);

    List<Notification> findByUserAndIsReadFalseOrderByCreatedAtDesc(User user);
}
