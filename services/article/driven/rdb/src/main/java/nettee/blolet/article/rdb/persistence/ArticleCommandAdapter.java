package nettee.blolet.article.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.ArticleCommandRepositoryPort;
import nettee.blolet.article.domain.Article;
import nettee.blolet.article.domain.sub.ArticleStatus;
import nettee.blolet.article.rdb.entity.type.builder.ArticleEntityStatus;
import nettee.blolet.article.rdb.mapper.ArticleEntityMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.blolet.article.exception.ArticleErrorCode.ARTICLE_NOT_FOUND;
import static nettee.blolet.article.exception.ArticleErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class ArticleCommandAdapter implements ArticleCommandRepositoryPort {

    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public Article save(Article article) {
        var articleEntity = articleEntityMapper.toEntity(article);
        try {
            var savedEntity = articleJpaRepository.save(articleEntity);
            articleJpaRepository.flush();
            return articleEntityMapper.toDomain(savedEntity);
        } catch (DataAccessException e) {
            throw DEFAULT.exception(e);
        }
    }

    @Override
    public Article update(Article article) {
        var existingArticle = articleJpaRepository.findById(Long.valueOf(article.getId()))
                .orElseThrow(ARTICLE_NOT_FOUND::exception);

        existingArticle.prepareArticleEntityUpdate()
                .title(article.getTitle())
                .content(article.getContent())
                .update();

        return articleEntityMapper.toDomain(existingArticle);
    }

    @Override
    public void updateStatus(String id, ArticleStatus articleStatus) {
        var existingArticle = articleJpaRepository.findById(Long.valueOf(id))
                .orElseThrow(ARTICLE_NOT_FOUND::exception);

        existingArticle.prepareArticleEntityStatusUpdate()
                .status(ArticleEntityStatus.valueOf(articleStatus))
                .updateStatus();
    }
}
