package nettee.article.usecase;

import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;

import java.util.List;

public interface ArticleReadUseCase {

    ArticleDetail getArticle(String articleId);
    List<ArticleSummary> getArticleList(String blogId);
}
