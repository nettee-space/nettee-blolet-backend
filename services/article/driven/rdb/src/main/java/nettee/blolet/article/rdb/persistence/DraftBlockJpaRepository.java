package nettee.blolet.article.rdb.persistence;

import nettee.blolet.article.rdb.entity.DraftBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftBlockJpaRepository extends JpaRepository<DraftBlockEntity, String> {
}
