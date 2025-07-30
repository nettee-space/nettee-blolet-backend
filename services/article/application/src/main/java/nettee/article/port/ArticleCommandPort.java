package nettee.article.port;

import nettee.article.domain.Article;

public interface ArticleCommandPort {
    Article save(Article article);
}
