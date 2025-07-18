package nettee.blolet.blog.readmodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

public final class BlogReadModels {

    public record BlogDetail(
            @Schema(description = "블로그 ID", example = "1")
            String id,
            @Schema(description = "블로그 이름", example = "Sun 好 Moon")
            String name,
            @Schema(description = "블로그 URL", example = "https://subdomain.blolet.com")
            String url,
            @Schema(description = "블로그 생성일시", example = "1996-10-27T14:30:00Z")
            Instant createdAt
    ) {}

    public record BlogSummary(
            @Schema(description = "블로그 ID", example = "1")
            String id,
            @Schema(description = "블로그 이름", example = "Sun 好 Moon")
            String name,
            @Schema(description = "블로그 URL", example = "https://subdomain.blolet.com")
            String url
    ) {}

    public record UserProfileBlogs(
            @Schema(description = "프로필 ID", example = "1")
            String profileId,
            @Schema(description = "사용자 이름", example = "wch1225")
            String username,
            @Schema(description = "사용자 별칭", example = "sun")
            String nickname,
            @Schema(description = "프로필 사진 URL", example = "https://api.blolet.com/profile/1/image/main")
            String profileImageUrl,
            @Schema(description = "프로필 사진 URL", example = "https://api.blolet.com/profile/1/image/thumbnail")
            String profileImageThumbnailUrl,
            List<BlogSummary> blogs
    ) {}
}
