package nettee.article.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.article.driven.rdb.persistence.mapper.ArticleEntityMapper;
import nettee.article.port.ArticleQueryRepositoryPort;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleQueryAdapter implements ArticleQueryRepositoryPort {

    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleEntityMapper mapper;

    @Override
    public Optional<ArticleDetail> findByArticleId(String articleId) {
        return articleJpaRepository.findDetailById(Long.valueOf(articleId))
                .map(mapper::toQueryModel);
    }

    @Override
    public Slice<ArticleSummary> findAllByBlogId(String blogId, Instant lastCreatedAt, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return articleJpaRepository
                .findByBlogIdAndCreatedAtBeforeOrderByCreatedAtDesc(Long.valueOf(blogId), lastCreatedAt, pageable)
                .map(mapper::toQueryModel);
    }
}