package nettee.blolet.article.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.blolet.article.application.port.ArticleLikesCommandRepositoryPort;
import nettee.blolet.article.domain.ArticleLikes;
import nettee.blolet.article.rdb.entity.ArticleLikesEntity;
import nettee.blolet.article.rdb.persistence.mapper.ArticleLikesEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

import static nettee.blolet.article.exception.ArticleErrorCode.ARTICLE_INVALID_ID_SPEC;
import static nettee.blolet.article.exception.ArticleErrorCode.ARTICLE_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class ArticleLikesCommandAdapter implements ArticleLikesCommandRepositoryPort {

    private final ArticleLikesJpaRepository jpaRepository;
    private final ArticleLikesEntityMapper mapper;

    @Override
    public Optional<ArticleLikes> findByProfileIdAndArticleId(String profileId, String articleId) {
        Long profileIdLong = Long.valueOf(profileId);
        Long articleIdLong = Long.valueOf(articleId);

        return jpaRepository.findByProfileIdAndArticleId(profileIdLong, articleIdLong)
                .map(mapper::toDomain);
    }

    @Override
    public ArticleLikes save(ArticleLikes domain) {
        ArticleLikesEntity targetEntity;

        Long profileId = Long.valueOf(domain.getProfileId());
        Long articleId = Long.valueOf(domain.getArticleId());

        // 도메인의 ID가 존재하지 않으면 DB에서 조회해서 생성 또는 업데이트, ID가 존재하면 업데이트
        if (domain.getId() == null) {
            targetEntity = jpaRepository.findByProfileIdAndArticleId(profileId, articleId)
                    .orElseGet(() -> mapper.toEntity(domain));
        } else {
            targetEntity = jpaRepository.findByProfileIdAndArticleId(profileId, articleId)
                    .orElseThrow(ARTICLE_NOT_FOUND::exception);

            // 요청받은 ID와 DB에서 조회한 데이터(targetEntity)의 ID가 일치하는지 확인
            String domainId = domain.getId();
            String entityId = targetEntity.getId().toString();
            if (!Objects.equals(domainId, entityId)) {
                throw ARTICLE_INVALID_ID_SPEC.exception();
            }
        }

        // upsert(생성 또는 업데이트)
        ArticleLikesEntity savedEntity = jpaRepository.save(targetEntity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public void deleteByProfileIdAndArticleId(String profileId, String articleId) {
        jpaRepository.deleteByProfileIdAndArticleId(Long.valueOf(profileId), Long.valueOf(articleId));
    }
}
