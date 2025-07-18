package nettee.article.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String id;

    private String blogId;

    private String entryBlockId;

    private String title;

    private String content;

    private String path;

    private Integer totalViews;

    private Integer totalLikes;

    private Integer totalShares;

    private ArticleStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
