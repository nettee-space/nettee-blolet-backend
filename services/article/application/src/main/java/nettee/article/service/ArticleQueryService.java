package nettee.article.service;

import lombok.RequiredArgsConstructor;
import nettee.article.port.ArticleQueryRepositoryPort;
import nettee.article.readmodel.ArticleQueryModels.ArticleDetail;
import nettee.article.readmodel.ArticleQueryModels.ArticleSummary;
import nettee.article.usecase.ArticleReadUseCase;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static nettee.article.exception.ArticleErrorCode.ARTICLE_NOT_FOUND;

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
    public List<ArticleSummary> getArticleList(String blogId, Instant lastCreatedAt, int size) {
        assert blogId != null : "blogId must not be null";
        assert lastCreatedAt != null : "lastCreatedAt must not be null";
        assert size > 0 : "size must be greater than 0";

        return articleQueryRepository.findAllByBlogId(blogId, lastCreatedAt, size);
    }
}
