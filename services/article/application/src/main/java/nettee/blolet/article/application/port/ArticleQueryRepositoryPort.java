package nettee.blolet.article.application.port;

import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleSummary;
import org.springframework.data.domain.Slice;

import java.time.Instant;
import java.util.Optional;

public interface ArticleQueryRepositoryPort {

    Optional<ArticleDetail> findByArticleId(String articleId);

    Slice<ArticleSummary> findAllByBlogId(String blogId, Instant lastCreatedAt, int size);
}
