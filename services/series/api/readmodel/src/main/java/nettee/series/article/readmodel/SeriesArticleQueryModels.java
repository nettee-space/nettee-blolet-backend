package nettee.series.article.readmodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;

public final class SeriesArticleQueryModels {

    private SeriesArticleQueryModels() {}

    @Builder
    public record SeriesArticleSummary(
            String seriesId,
            String articleId,
            String draftId,
            @Schema(
                    description = "최근 제목 (아티클이 있다면 아티클의 제목, 아니라면 드래프트의 제목)",
                    example = "테토남은 예시 같은 거 디테일하게 쓰지 않는다."
            )
            String title,
            Integer displayOrder,
            Instant createdAt,
            Instant updatedAt
    ) {
    }
}
