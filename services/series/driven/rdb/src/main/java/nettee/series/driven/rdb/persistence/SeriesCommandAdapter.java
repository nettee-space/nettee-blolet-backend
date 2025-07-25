package nettee.series.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.series.application.port.SeriesCommandRepositoryPort;
import nettee.series.domain.Series;
import nettee.series.driven.rdb.persistence.mapper.SeriesEntityMapper;
import org.springframework.stereotype.Repository;

import static nettee.series.exception.SeriesErrorCode.SERIES_NOT_FOUND;

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
                .banner(seriesEntityMapper.base64ToBytes(series.getBanner()))
                .displayOrder(series.getDisplayOrder())
                .update();
        
        return seriesEntityMapper.toDomain(existsSeries);
    }
    
    @Override
    public void delete(String seriesId) {
        seriesJpaRepository.deleteById(Long.valueOf(seriesId));
    }
}
