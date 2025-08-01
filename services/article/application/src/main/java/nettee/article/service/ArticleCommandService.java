package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.Article;
import nettee.article.port.ArticleCommandPort;
import nettee.article.usecase.ArticleCreateUseCase;
import nettee.article.usecase.ArticleUpdateUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandService implements ArticleCreateUseCase, ArticleUpdateUseCase {

    private final ArticleCommandPort articleCommandPort;

    @Override
    public Article createArticle(Article article) {
        return articleCommandPort.save(article);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleCommandPort.update(article);
    }
}
