package nettee.blolet.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.SeriesQueryRepositoryPort;
import nettee.blolet.article.application.usecase.SeriesReadUseCase;
import nettee.blolet.article.application.usecase.SeriesVisitUseCase;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesSummary;
import nettee.blolet.blog.export.client.api.BlogClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static nettee.blolet.article.exception.SeriesErrorCode.SERIES_NOT_FOUND;
import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NOT_IMPLEMENTED_FEATURE;

@Service
@RequiredArgsConstructor
public class SeriesQueryService implements SeriesReadUseCase, SeriesVisitUseCase {
    
    private final SeriesQueryRepositoryPort queryRepositoryPort;
    private final BlogClient blogClient;
    
    @Override
    public SeriesDetail findDetailForOwner(String seriesId, String userId) {
        assert seriesId != null;
        assert userId != null;

        Set<String> blogIds = blogClient.getBlogIdsByUserId(userId)
                .blogIds();
        if (blogIds.size() != 1) throw BLOG_NOT_IMPLEMENTED_FEATURE.exception();

        String userBlogId = blogIds.iterator().next();

        return queryRepositoryPort.findByIdAndOwnership(seriesId, userBlogId)
                .orElseThrow(SERIES_NOT_FOUND::exception);
    }

    @Override
    public List<SeriesSummary> getSeriesList(String blogId) {
        assert blogId != null;
        
        return queryRepositoryPort.findAllByBlogId(blogId);
    }

    @Override
    public SeriesDetail findDetailForPublic(String seriesId) {
        return queryRepositoryPort.findExceptDraftsById(seriesId)
                .orElseThrow(SERIES_NOT_FOUND::exception);
    }
}
