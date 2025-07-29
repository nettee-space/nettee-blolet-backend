package nettee.article.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Long id;

    private Long blogId;

    private Long entryBlockId;

    private String title;

    private String content;

    private String path;

    @Builder.Default
    private Integer totalViews = 0;

    @Builder.Default
    private Integer totalLikes = 0;

    @Builder.Default
    private Integer totalShares = 0;

    private ArticleStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
