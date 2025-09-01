package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.port.ArticleQueryRepositoryPort;
import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.blolet.article.readmodel.ArticleQueryModels.ArticleSummary;
import nettee.article.usecase.ArticleReadUseCase;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static nettee.blolet.article.exception.ArticleErrorCode.ARTICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ArticleQueryService implements ArticleReadUseCase {

    private final ArticleQueryRepositoryPort articleQueryRepository;

    @Override
    public ArticleDetail getArticle(String articleId) {
        assert articleId != null;

        return articleQueryRepository.findByArticleId(articleId)
                .orElseThrow(ARTICLE_NOT_FOUND::exception);
    }

    @Override
    public Slice<ArticleSummary> getArticleList(String blogId, Instant lastCreatedAt, int size) {
        assert blogId != null : "blogId must not be null";
        assert lastCreatedAt != null : "lastCreatedAt must not be null";
        assert size > 0 : "size must be greater than 0";

        return articleQueryRepository.findAllByBlogId(blogId, lastCreatedAt, size);
    }
}
