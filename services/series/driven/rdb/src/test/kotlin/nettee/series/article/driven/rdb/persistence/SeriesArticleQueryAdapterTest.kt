package nettee.series.article.driven.rdb.persistence

import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import nettee.series.article.driven.rdb.entity.SeriesArticleEntity
import nettee.series.article.driven.rdb.persistence.mapper.SeriesArticleEntityMapper
import nettee.series.driven.rdb.jpa.JpaTransactionalFreeSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackageClasses = [SeriesArticleEntityMapper::class])
class SeriesArticleQueryAdapterTest(
    @Autowired private val seriesArticleJpaRepository: SeriesArticleJpaRepository,
    @Autowired private val mapper: SeriesArticleEntityMapper,
    @Autowired private val entityManager: EntityManager,
) : JpaTransactionalFreeSpec({

    val adapter = SeriesArticleQueryAdapter(mapper)

    adapter.setEntityManager(entityManager)

    val testSeriesId = "1"
    val testDraftId = 100L
    val testArticleId = 200L

    "[정상] findBySeriesId - 시리즈 ID로 조회" - {
        val result = adapter.findBySeriesId(testSeriesId)

        "조회된 결과 개수는 2개여야 한다" {
            result.size shouldBe 2
        }

        "조회된 결과의 필드가 일치해야 한다" {
            result[0].seriesId shouldBe testSeriesId
            result[0].draftId shouldBe testDraftId.toString()
            result[0].articleId shouldBe testArticleId.toString()

            result[1].seriesId shouldBe testSeriesId
            result[1].draftId shouldBe (testDraftId + 1).toString()
            result[1].articleId shouldBe (testArticleId + 1).toString()
        }
    }

    beforeSpec {
        (0..1).forEach {
            val entity = SeriesArticleEntity().apply {
                seriesId = testSeriesId.toLong()
                draftId = testDraftId + it
                articleId = testArticleId + it
            }
            seriesArticleJpaRepository.save(entity)
        }
    }
})