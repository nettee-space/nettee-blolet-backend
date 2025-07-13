package nettee.usersettings.driven.rdb.persistence;

import nettee.usersettings.driven.rdb.entity.NotificationSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationSettingsJpaRepository extends JpaRepository<NotificationSettingsEntity, Long> {

}
