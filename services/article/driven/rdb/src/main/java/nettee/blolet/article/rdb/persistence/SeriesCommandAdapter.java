package nettee.blolet.article.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.SeriesCommandRepositoryPort;
import nettee.blolet.article.domain.Series;
import nettee.blolet.article.rdb.mapper.SeriesEntityMapper;
import org.springframework.stereotype.Repository;

import static nettee.blolet.article.exception.SeriesErrorCode.SERIES_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class SeriesCommandAdapter implements SeriesCommandRepositoryPort {
    
    private final SeriesJpaRepository seriesJpaRepository;
    private final SeriesEntityMapper seriesEntityMapper;
    
    @Override
    public boolean existsByBlogIdAndTitle(String blogId, String title) {
        return seriesJpaRepository.existsByBlogIdAndTitle(Long.valueOf(blogId), title);
    }
    
    @Override
    public Series save(Series series) {
        var seriesEntity = seriesEntityMapper.toEntity(series);

        return seriesEntityMapper.toDomain(seriesJpaRepository.save(seriesEntity));
    }
    
    @Override
    public Series update(Series series) {
        var existsSeries = seriesJpaRepository.findById(Long.valueOf(series.getId()))
                .orElseThrow(SERIES_NOT_FOUND::exception);
        
        existsSeries.prepareUpdate()
                .title(series.getTitle())
                .description(series.getDescription())
                .banner(series.getBannerUrl())
                .displayOrder(series.getDisplayOrder())
                .update();
        
        return seriesEntityMapper.toDomain(existsSeries);
    }
    
    @Override
    public void delete(String seriesId) {
        seriesJpaRepository.deleteById(Long.valueOf(seriesId));
    }
}
