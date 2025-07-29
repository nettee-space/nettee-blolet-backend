package nettee.article.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.Article;
import nettee.article.driven.rdb.persistence.mapper.ArticleEntityMapper;
import nettee.article.port.ArticleCommandPort;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import static nettee.article.exception.ArticleErrorCode.DEFAULT;

@Repository
@RequiredArgsConstructor
public class ArticleCommandAdapter implements ArticleCommandPort {

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

}
