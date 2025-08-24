package nettee.draft.driven.rdb;

import nettee.draft.driven.rdb.entity.DraftImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftImageJpaRepository extends JpaRepository<DraftImageEntity, String> {
}
