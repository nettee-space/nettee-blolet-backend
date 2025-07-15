package nettee.notification.driven.rdb.persistence;

import nettee.notification.driven.rdb.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {

}
