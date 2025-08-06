package nettee.article.port;

import nettee.article.domain.Article;
import nettee.article.domain.ArticleStatus;

public interface ArticleCommandRepositoryPort {

    Article save(Article article);
    Article update(Article article);
    void updateStatus(String id, ArticleStatus status);
}
