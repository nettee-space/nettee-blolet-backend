package nettee.blolet.article.rdb.persistence

import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import nettee.blolet.article.rdb.entity.SeriesEntity
import nettee.blolet.article.rdb.jpa.JpaTransactionalFreeSpec
import nettee.blolet.article.rdb.mapper.SeriesEntityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackageClasses = [SeriesEntityMapper::class])
class SeriesQueryAdapterTest(
    @Autowired private val entityManager: EntityManager,
    @Autowired private val seriesEntityMapper: SeriesEntityMapper,
    @Autowired private val seriesJpaRepository: SeriesJpaRepository
) : JpaTransactionalFreeSpec({

    val adapter = SeriesQueryAdapter(seriesEntityMapper)

    adapter.setEntityManager(entityManager)

    val testBlogId = 123L
    val seriesId = 1L

    "[정상] findBySeriesId - 존재하는 경우" - {
        val result = adapter.findByIdAndOwnership(seriesId.toString(), testBlogId.toString())

        "Optional 값이 존재해야 한다" {
            result.isPresent shouldBe true
        }

        "값이 일치해야 한다" {
            val detail = result.get()
            detail.id shouldBe seriesId.toString()
            detail.title shouldBe "테스트 시리즈"
        }
    }

    "[정상] findAllByBlogId - 블로그 ID로 전체 조회" - {
        val result = adapter.findAllByBlogId(testBlogId.toString())

        "결과는 2건이어야 한다" {
            result.size shouldBe 2
        }

        "값이 일치해야 한다" {
            result[0].blogId shouldBe testBlogId.toString()
            result[1].blogId shouldBe testBlogId.toString()
        }
    }

    beforeSpec {
        (0..1).forEach {
            val entity = SeriesEntity().apply {
                blogId = testBlogId
                title = "테스트 시리즈"
                description = "설명입니다"
                displayOrder = 1
            }
            seriesJpaRepository.save(entity)
        }
    }
})
