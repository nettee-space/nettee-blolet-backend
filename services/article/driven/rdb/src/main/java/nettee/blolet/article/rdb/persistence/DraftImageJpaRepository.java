package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.rdb.entity.DraftImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftImageJpaRepository extends JpaRepository<DraftImageEntity, String> {
}
