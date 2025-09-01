package nettee.series.application.services;

import lombok.RequiredArgsConstructor;
import nettee.blolet.blog.export.client.api.BlogClient;
import nettee.series.application.port.SeriesQueryRepositoryPort;
import nettee.series.application.usecase.SeriesReadUseCase;
import nettee.series.application.usecase.SeriesVisitUseCase;
import nettee.series.readmodel.SeriesQueryModels.SeriesDetail;
import nettee.series.readmodel.SeriesQueryModels.SeriesSummary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static nettee.blolet.blog.exception.BlogErrorCode.BLOG_NOT_IMPLEMENTED_FEATURE;
import static nettee.series.exception.SeriesErrorCode.SERIES_NOT_FOUND;

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
    public SeriesDetail visitSeries(String seriesId) {
        return queryRepositoryPort.findExceptDraftsById(seriesId)
                .orElseThrow(SERIES_NOT_FOUND::exception);
    }
}
