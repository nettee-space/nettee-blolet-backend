package nettee.article.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.Article;
import nettee.article.domain.ArticleStatus;
import nettee.article.driven.rdb.entity.type.builder.ArticleEntityStatus;
import nettee.article.driven.rdb.persistence.mapper.ArticleEntityMapper;
import nettee.article.port.ArticleCommandRepositoryPort;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.article.exception.ArticleErrorCode.ARTICLE_NOT_FOUND;
import static nettee.article.exception.ArticleErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class ArticleCommandRepositoryAdapter implements ArticleCommandRepositoryPort {

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
        var existingArticle = articleJpaRepository.findById(article.getId())
                .orElseThrow(ARTICLE_NOT_FOUND::exception);

        existingArticle.prepareArticleEntityUpdate()
                .title(article.getTitle())
                .content(article.getContent())
                .update();;

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
