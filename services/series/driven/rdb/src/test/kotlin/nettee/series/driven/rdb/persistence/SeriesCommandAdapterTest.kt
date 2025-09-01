package nettee.series.driven.rdb.persistence

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import nettee.blolet.article.domain.Series
import nettee.blolet.article.exception.SeriesErrorCode.SERIES_NOT_FOUND
import nettee.blolet.article.exception.SeriesException
import nettee.series.driven.rdb.entity.SeriesEntity
import nettee.series.driven.rdb.jpa.JpaTransactionalFreeSpec
import nettee.series.driven.rdb.persistence.mapper.SeriesEntityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan

@DataJpaTest
@ComponentScan(basePackageClasses = [SeriesEntityMapper::class])
class SeriesCommandAdapterTest(
    @Autowired private val repository: SeriesJpaRepository,
    @Autowired private val mapper: SeriesEntityMapper
) : JpaTransactionalFreeSpec({

    val adapter = SeriesCommandAdapter(repository, mapper)

    val testBlogId = "1"
    val testTitle = "Test Series"
    val testDescription = "Test Description"
    val testBanner = null
    val testDisplayOrder = 1

    "[정상] existsByBlogIdAndTitle - 존재하는 경우" - {
        repository.save(SeriesEntity().apply {
            blogId = testBlogId.toLong()
            title = testTitle
        })

        val result = adapter.existsByBlogIdAndTitle(testBlogId, testTitle)

        "존재 여부는 true여야 한다" {
            result shouldBe true
        }
    }

    "[정상] save" - {
        val series =
            Series(
                null,
                testBlogId,
                testTitle,
                testDescription,
                testBanner,
                testDisplayOrder,
                null,
                null,
                null
            )

        val result = adapter.save(series)

        "저장된 Series의 ID가 생성되어야 한다" {
            result.id shouldNotBe null
        }

        "Series 속성이 일치해야 한다" {
            result.title shouldBe testTitle
            result.description shouldBe testDescription
            result.banner shouldBe testBanner
            result.displayOrder shouldBe testDisplayOrder
        }
    }

    "[정상] update" - {
        val savedEntity = repository.save(SeriesEntity().apply {
            blogId = testBlogId.toLong()
            title = testTitle
            description = "Old Description"
            displayOrder = 0
        })

        val seriesUpdate = Series(
            savedEntity.id.toString(),
            testBlogId,
            "Updated Title",
            "Updated Description",
            testBanner,
            2,
            null,
            null,
            null
        )

        val result = adapter.update(seriesUpdate)

        "업데이트된 Series 정보가 일치해야 한다" {
            result.title shouldBe "Updated Title"
            result.description shouldBe "Updated Description"
            result.displayOrder shouldBe 2
        }
    }

    "[예외] update - 존재하지 않는 경우" - {
        val seriesUpdate = Series(
            "9999",
            testBlogId,
            "Non-existent Series",
            testDescription,
            testBanner,
            testDisplayOrder,
            null,
            null,
            null
        )

        val exception = shouldThrow<SeriesException> {
            adapter.update(seriesUpdate)
        }

        "예외 코드가 SERIES_NOT_FOUND 여야 한다" {
            exception.errorCode shouldBe SERIES_NOT_FOUND
        }
    }

    "[정상] delete" - {
        val savedEntity = repository.save(SeriesEntity().apply {
            blogId = testBlogId.toLong()
            title = testTitle
        })

        adapter.delete(savedEntity.id.toString())

        val deleted = repository.findById(savedEntity.id)

        "삭제된 엔티티는 존재하지 않아야 한다" {
            deleted.isPresent shouldBe false
        }
    }
})