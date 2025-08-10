package nettee.article.port;

import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;

import java.util.List;
import java.util.Optional;

public interface ArticleQueryRepositoryPort {

    Optional<ArticleDetail> findByArticleId(String articleId);

    List<ArticleSummary> findAllByBlogId(String blogId);
}
