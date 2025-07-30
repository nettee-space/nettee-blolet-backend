package nettee.series.application.port;

import nettee.series.domain.Series;

public interface SeriesCommandRepositoryPort {
    
    boolean existsByBlogIdAndTitle(String blogId, String title);
    
    long countByBlogId(String blogId);
    
    Series save(Series series);
    
    Series update(Series series);
    
    void delete(String seriesId);
}
