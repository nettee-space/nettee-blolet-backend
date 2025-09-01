package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.sub.DraftStatus;
import nettee.blolet.article.readmodel.DraftReadModels.DraftDetail;
import nettee.blolet.article.readmodel.DraftReadModels.DraftSummary;
import nettee.blolet.article.readmodel.DraftReadModels.DraftTitle;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface DraftQueryPort {
    Optional<DraftDetail> findById(String id);
    Map<String, DraftTitle> findTitlesById(Set<String> id);
    List<DraftSummary> findByStatuses(String blogId, Set<DraftStatus> statuses, String sortBy, boolean ascending);

}
