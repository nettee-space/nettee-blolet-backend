package nettee.series.application.port;

import nettee.series.domain.Series;

public interface SeriesCommandRepositoryPort {
    
    Boolean existsByBlogIdAndTitle(String blogId, String title);

    Series save (Series series);
    
    Series update (Series series);
    
    void delete (String blogId, String seriesId);
}
