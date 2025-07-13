package nettee.series.application.services;

import lombok.RequiredArgsConstructor;
import nettee.series.application.port.SeriesCommandRepositoryPort;
import nettee.series.application.usecase.SeriesCreateUseCase;
import nettee.series.application.usecase.SeriesDeleteUseCase;
import nettee.series.application.usecase.SeriesUpdateUseCase;
import nettee.series.domain.Series;
import nettee.series.exception.SeriesException;
import org.springframework.stereotype.Service;

import static nettee.series.exception.SeriesErrorCode.SERIES_ALREADY_EXIST;

@Service
@RequiredArgsConstructor
public class SeriesCommandService implements SeriesCreateUseCase, SeriesUpdateUseCase, SeriesDeleteUseCase {
    
    private final SeriesCommandRepositoryPort commandRepositoryPort;
    
    @Override
    public Series createSeries(Series series) {
        // 블로그의 존재와 제목 존재 여부 확인
        assert series.getBlogId() != null;
        assert series.getTitle() != null;
        
        // 시리즈 제목 중복 체크
        if(commandRepositoryPort.existsByBlogIdAndTitle(series.getBlogId(), series.getTitle())){
            throw new SeriesException(SERIES_ALREADY_EXIST);
        }
       
        // 시리즈 저장
        Series newSeries = commandRepositoryPort.save(series);
       
        // 시리즈에 시리즈 게시글 목록이 존재할 경우 게시글 목록 저장
        if (series.getSeriesArticleList() != null && !series.getSeriesArticleList().isEmpty()) {
            // TODO 시리즈 게시글 목록 저장 로직 추가
        }
        
        return newSeries;
    }
    
    @Override
    public Series updateSeries(Series series) {
        assert series.getTitle() != null;
        
        // 시리즈 제목 중복 체크
        if(commandRepositoryPort.existsByBlogIdAndTitle(series.getBlogId(), series.getTitle())){
            throw new SeriesException(SERIES_ALREADY_EXIST);
        }
        
        // 시리즈 수정
        Series updatedSeries = commandRepositoryPort.update(series);
        
        // 시리즈내에 시리즈 게시글이 존재할 경우 게시글 목록 저장
        if (series.getSeriesArticleList() != null && !series.getSeriesArticleList().isEmpty()) {
            // TODO 기존 시리즈 게시글 목록 삭제 후 저장 로직 추가
        }
        
        return updatedSeries;
    }
    
    @Override
    public void deleteSeries(String blogId, String seriesId) {
        assert blogId != null;
        assert seriesId != null;
        
        // 시리즈 삭제
        commandRepositoryPort.delete(blogId,seriesId);
        
        // 시리즈내에 게시글이 존재 시, 해당 매핑 정보 삭제
        // TODO 해당 시리즈 게시물의 시리즈 삭제
    }
}
