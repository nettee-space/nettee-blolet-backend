package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.Series;

public interface SeriesCommandRepositoryPort {
    
    boolean existsByBlogIdAndTitle(String blogId, String title);
    
    Series save(Series series);
    
    Series update(Series series);
    
    void delete(String seriesId);
}
