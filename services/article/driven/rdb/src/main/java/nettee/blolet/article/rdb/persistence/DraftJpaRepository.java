package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.rdb.entity.DraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftJpaRepository extends JpaRepository<DraftEntity, String> {
}
