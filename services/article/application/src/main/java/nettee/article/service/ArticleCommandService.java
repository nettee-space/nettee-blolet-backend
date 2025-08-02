package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.Article;
import nettee.article.domain.ArticleStatus;
import nettee.article.port.ArticleCommandRepositoryPort;
import nettee.article.usecase.ArticleCreateUseCase;
import nettee.article.usecase.ArticleDeleteUseCase;
import nettee.article.usecase.ArticleUpdateUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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
        articleCommandRepository.updateStatus(id, ArticleStatus.DELETED);
    }
}
