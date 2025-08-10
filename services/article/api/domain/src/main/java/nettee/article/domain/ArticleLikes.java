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
public class ArticleLikes {

    private String id;

    private String userId;

    private String profileId;

    private String articleId;

    private Integer count;

    private ArticleLikesStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    public void increaseCount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        this.count += amount;
    }
}
