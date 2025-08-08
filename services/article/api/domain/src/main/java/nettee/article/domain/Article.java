package nettee.article.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@ToString(callSuper = true)
@EqualsAndHashCode
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
