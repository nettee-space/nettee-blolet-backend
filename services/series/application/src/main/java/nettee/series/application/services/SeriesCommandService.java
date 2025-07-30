package nettee.series.application.services;

import lombok.RequiredArgsConstructor;
import nettee.series.application.port.SeriesCommandRepositoryPort;
import nettee.series.application.usecase.SeriesCreateUseCase;
import nettee.series.application.usecase.SeriesDeleteUseCase;
import nettee.series.application.usecase.SeriesUpdateUseCase;
import nettee.series.article.application.usecase.SeriesArticleCreateUseCase;
import nettee.series.article.application.usecase.SeriesArticleDeleteUseCase;
import nettee.series.article.domain.SeriesArticle;
import nettee.series.domain.Series;
import nettee.series.exception.SeriesException;
import org.springframework.stereotype.Service;

import java.util.List;

import static nettee.series.exception.SeriesErrorCode.SERIES_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class SeriesCommandService implements SeriesCreateUseCase, SeriesUpdateUseCase, SeriesDeleteUseCase {
    
    private final SeriesCommandRepositoryPort commandRepositoryPort;
    private final SeriesArticleCreateUseCase seriesArticleCreateUseCase;
    private final SeriesArticleDeleteUseCase seriesArticleDeleteUseCase;
    
    @Override
    public Series createSeries(Series series) {
        assert series.getBlogId() != null;
        assert series.getTitle() != null;
        
        // 시리즈 제목 중복 체크
        if (commandRepositoryPort.existsByBlogIdAndTitle(series.getBlogId(), series.getTitle())) {
            throw new SeriesException(SERIES_ALREADY_EXIST);
        }
        
        // 시리즈 정렬순서 기본값 설정
        if (series.getDisplayOrder() == null) {
            var seriesCnt = commandRepositoryPort.countByBlogId(series.getBlogId());
            
            series.prepareUpdate()
                    .displayOrder(Long.valueOf(seriesCnt).intValue() + 1)
                    .update();
        }
        
        // 시리즈 저장
        Series newSeries = commandRepositoryPort.save(series);
        
        // 시리즈에 시리즈 게시글 목록이 존재할 경우 게시글 목록 저장
        if (series.getSeriesArticleList() != null && !series.getSeriesArticleList().isEmpty()) {
            List<SeriesArticle> newSeriesArticleList = seriesArticleCreateUseCase.createSeriesArticleList(newSeries.getId(), series.getSeriesArticleList());
            
            newSeries.prepareUpdate()
                    .seriesArticleList(newSeriesArticleList)
                    .update();
        }
        
        return newSeries;
    }
    
    @Override
    public Series updateSeries(Series series) {
        assert series.getTitle() != null;
        
        // 시리즈 제목 중복 체크
        if (commandRepositoryPort.existsByBlogIdAndTitle(series.getBlogId(), series.getTitle())) {
            throw new SeriesException(SERIES_ALREADY_EXIST);
        }
        
        // 시리즈 수정
        Series updatedSeries = commandRepositoryPort.update(series);
        
        // 시리즈내에 시리즈 게시글이 존재할 경우 게시글 목록 저장
        if (series.getSeriesArticleList() != null && !series.getSeriesArticleList().isEmpty()) {
            // 시리즈 게시글 전체 삭제 후
            seriesArticleDeleteUseCase.deleteSeriesArticleList(updatedSeries.getId());
            // 시리즈 게시글 새로 추가
            seriesArticleCreateUseCase.createSeriesArticleList(updatedSeries.getId(), series.getSeriesArticleList());
        }
        
        return updatedSeries;
    }
    
    @Override
    public void deleteSeries(String seriesId) {
        assert seriesId != null;
        
        // 시리즈 삭제
        commandRepositoryPort.delete(seriesId);
        
        // 시리즈내에 게시글이 존재 시, 해당 매핑 정보 삭제
        seriesArticleDeleteUseCase.deleteSeriesArticleList(seriesId);
    }
}
