package nettee.draft.draftblock.driven.rdb;

import nettee.draft.draftblock.driven.rdb.entity.DraftBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftBlockJpaRepository extends JpaRepository<DraftBlockEntity, String> {
}
