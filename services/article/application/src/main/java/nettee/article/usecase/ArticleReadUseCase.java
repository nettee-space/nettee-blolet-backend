package nettee.article.usecase;

import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import org.springframework.data.domain.Slice;

import java.time.Instant;

public interface ArticleReadUseCase {

    ArticleDetail getArticle(String articleId);

    Slice<ArticleSummary> getArticleList(String blogId, Instant lastCreatedAt, int size);
}
