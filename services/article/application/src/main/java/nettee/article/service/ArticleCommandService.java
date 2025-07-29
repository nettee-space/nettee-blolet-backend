package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.Article;
import nettee.article.port.ArticleCommandPort;
import nettee.article.usecase.ArticleCreateUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandService implements ArticleCreateUseCase {
    private final ArticleCommandPort articleCommandPort;
    @Override
    public Article createArticle(Article article) {
        return articleCommandPort.save(article);
    }
}
