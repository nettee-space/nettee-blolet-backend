package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.sub.DraftBlockStatus;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockDetail;
import nettee.blolet.article.readmodel.DraftBlockReadModels.DraftBlockSummary;

import java.util.List;
import java.util.Optional;

public interface DraftBlockQueryPort {
    Optional<DraftBlockDetail> findById(String id);
    List<DraftBlockSummary> findByStatus(String articleId, DraftBlockStatus status);
}
