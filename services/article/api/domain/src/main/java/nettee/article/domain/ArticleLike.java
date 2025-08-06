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
public class ArticleLike {

    private String id;

    private String userId;

    private String profileId;

    private String articleId;

    private String count;

    private ArticleLikeStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
