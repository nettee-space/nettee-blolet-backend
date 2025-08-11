package nettee.article.driven.rdb.persistence;

import lombok.RequiredArgsConstructor;
import nettee.article.domain.ArticleLikes;
import nettee.article.driven.rdb.entity.ArticleLikesEntity;
import nettee.article.driven.rdb.persistence.mapper.ArticleLikesEntityMapper;
import nettee.article.port.ArticleLikesCommandRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static nettee.article.exception.ArticleErrorCode.ARTICLE_INVALID_ID_SPEC;
import static nettee.article.exception.ArticleErrorCode.ARTICLE_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class ArticleLikesCommandAdapter implements ArticleLikesCommandRepositoryPort {

    private final ArticleLikesJpaRepository jpaRepository;
    private final ArticleLikesEntityMapper mapper;

    @Override
    public ArticleLikes save(ArticleLikes domain) {
        ArticleLikesEntity targetEntity;

        // 도메인의 ID가 존재하지 않으면 새로 생성, 존재하면 업데이트
        if (domain.getId() == null) {
            targetEntity = mapper.toEntity(domain);
        } else {
            targetEntity = jpaRepository.findByProfileIdAndArticleId(domain.getProfileId(), domain.getArticleId())
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
        jpaRepository.deleteByProfileIdAndArticleId(profileId, articleId);
    }
}
