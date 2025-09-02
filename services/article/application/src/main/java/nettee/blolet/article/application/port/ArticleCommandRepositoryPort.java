package nettee.blolet.article.application.port;

import nettee.blolet.article.domain.Article;
import nettee.blolet.article.domain.sub.ArticleStatus;

public interface ArticleCommandRepositoryPort {

    Article save(Article article);
    Article update(Article article);
    void updateStatus(String id, ArticleStatus status);
}
