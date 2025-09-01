package nettee.blolet.article.application.service;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.domain.Article;
import nettee.blolet.article.domain.sub.ArticleStatus;
import nettee.blolet.article.application.port.ArticleCommandRepositoryPort;
import nettee.blolet.article.application.usecase.ArticleCreateUseCase;
import nettee.blolet.article.application.usecase.ArticleDeleteUseCase;
import nettee.blolet.article.application.usecase.ArticleUpdateUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleCommandService implements ArticleCreateUseCase, ArticleUpdateUseCase, ArticleDeleteUseCase {

    private final ArticleCommandRepositoryPort articleCommandRepository;

    @Override
    public Article createArticle(Article article) {
        return articleCommandRepository.save(article);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleCommandRepository.update(article);
    }

    @Override
    public void deleteArticle(String id) {
        articleCommandRepository.updateStatus(id, ArticleStatus.REMOVED);
    }
}
