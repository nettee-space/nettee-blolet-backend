package nettee.series.article.driven.rdb.persistence

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import nettee.blolet.article.domain.SeriesArticle
import nettee.blolet.article.exception.SeriesArticleErrorCode.SERIES_ARTICLE_NOT_FOUND
import nettee.blolet.article.exception.SeriesArticleException
import nettee.series.article.driven.rdb.entity.SeriesArticleEntity
import nettee.series.article.driven.rdb.persistence.mapper.SeriesArticleEntityMapper
import nettee.series.driven.rdb.jpa.JpaTransactionalFreeSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackageClasses = [SeriesArticleEntityMapper::class])
class SeriesArticleCommandAdapterTest(
    @Autowired private val repository: SeriesArticleJpaRepository,
    @Autowired private val mapper: SeriesArticleEntityMapper
) : JpaTransactionalFreeSpec({

    val adapter = SeriesArticleCommandAdapter(repository, mapper)

    val testSeriesId = "1"
    val testDraftId = "100"
    val testArticleId = "200"

    "[정상] findByIdAndDraftId - 존재하는 경우" - {
        val entity = SeriesArticleEntity().apply {
            seriesId = 1L
            draftId = 100L
            articleId = null
        }

        repository.save(entity)

        val result = adapter.findBySeriesIdAndDraftId(testSeriesId, testDraftId)

        "조회된 결과가 존재해야 한다" {
            result.isPresent shouldBe true
            result.get().seriesId shouldBe testSeriesId
            result.get().draftId shouldBe testDraftId
        }
    }

    "[정상] saveAll" - {
        val articles = listOf(
            SeriesArticle("1", null, "101", null, null, null),
            SeriesArticle("1", null, "102", null, null, null),
        )

        val result = adapter.saveAll(articles)

        "저장된 목록은 2개여야 한다" {
            result.size shouldBe 2
        }

        "각 저장된 항목은 id가 생성되어야 한다" {
            result.forEach { it.seriesId shouldNotBe null }
        }
    }

    "[정상] updateDraftToArticle" - {
        val entity = SeriesArticleEntity().apply {
            seriesId = 1L
            draftId = 300L
            articleId = null
        }

        repository.save(entity)

        val updatedArticle =
            SeriesArticle("1", testArticleId, "300", null, null, null)

        val result = adapter.updateDraftToArticle(updatedArticle)

        "업데이트된 articleId가 설정되어야 한다" {
            result.articleId shouldBe testArticleId
        }
    }

    "[예외] updateDraftToArticle - 존재하지 않는 경우" - {
        val nonExistentArticle =
            SeriesArticle("999", testArticleId, "999", null, null, null)


        val exception = shouldThrow<SeriesArticleException> {
            adapter.updateDraftToArticle(nonExistentArticle)
        }

        "예외 코드가 SERIES_ARTICLE_NOT_FOUND 이어야 한다" {
            exception.errorCode shouldBe SERIES_ARTICLE_NOT_FOUND
        }
    }

    "[정상] delete" - {
        val entity = SeriesArticleEntity().apply {
            seriesId = 1L
            draftId = 100L
            articleId = null
        }

        repository.save(entity)

        adapter.deleteAllBySeriesId(testSeriesId)

        val deleted = repository.findById(entity.seriesId)

        "삭제된 엔티티는 존재하지 않아야 한다" {
            deleted.isPresent shouldBe false
        }
    }
})
