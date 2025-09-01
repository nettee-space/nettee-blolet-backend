package nettee.blolet.article.rdb

import io.kotest.core.spec.style.FreeSpec
import nettee.blolet.article.web.SeriesQueryApi
import nettee.blolet.article.application.usecase.SeriesReadUseCase
import nettee.blolet.article.application.usecase.SeriesVisitUseCase
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesDetail
import nettee.blolet.article.readmodel.SeriesQueryModels.SeriesSummary
import nettee.blolet.jwt.filter.resolver.AuthUserArgumentResolver
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.request
import java.time.Instant

@WebMvcTest(SeriesQueryApi::class)
@Import(
    AuthUserArgumentResolver::class,
    ResolverTestConfig::class,
)
class SeriesQueryApiTest(
    @Autowired private val mvc: MockMvc,
    @MockitoBean private val seriesReadUseCase: SeriesReadUseCase,
    @MockitoBean private val seriesVisitUseCase: SeriesVisitUseCase,
) : FreeSpec({

    // given - 테스트 데이터 생성
    val now = Instant.now()

    val sampleSeriesSummaryList = listOf(
        SeriesSummary.builder()
            .id("1")
            .blogId("1")
            .title("시리즈 A")
            .displayOrder(1)
            .createdAt(now)
            .updatedAt(now)
            .build()
    )

    val sampleSeriesDetail = SeriesDetail.builder()
        .id("1")
        .blogId("1")
        .title("시리즈 A")
        .description("시리즈 설명")
        .bannerUrl(null)
        .articles(emptyList())
        .createdAt(now)
        .updatedAt(now)
        .build()

    val mvcRequest: (HttpMethod, String, Map<String, Any>) -> ResultActionsDsl =
        { method, url, pathVariables ->
            mvc.request(method, url, *pathVariables.values.toTypedArray()) {
                contentType = MediaType.APPLICATION_JSON
            }
        }

    "[GET] 시리즈 목록 조회" - {
        val blogId = "1"

        "[정상 요청] 2xx 응답 및 목록 반환" {
            // mock
            `when`(seriesReadUseCase.getSeriesList(blogId)).thenReturn(sampleSeriesSummaryList)

            mvcRequest(HttpMethod.GET, "/blogs/{blogId}/series", mapOf("blogId" to blogId))
                .andExpect {
                    status { is2xxSuccessful() }
                    jsonPath("$.seriesList.size()") { value(1) }
                    jsonPath("$.seriesList[0].id") { value("1") }
                    jsonPath("$.seriesList[0].title") { value("시리즈 A") }
                    jsonPath("$.seriesList[0].displayOrder") { value(1) }
                }
                .andDo { print() }
        }
    }

    "[GET] 시리즈 상세 조회" - {
        val seriesId = "1"
        val userId = "1"

        "[정상 요청] 시 2xx 응답 및 상세 데이터 반환" {
            // mock
            `when`(seriesReadUseCase.findDetailForOwner(seriesId, userId))
                .thenReturn(sampleSeriesDetail)
            `when`(seriesVisitUseCase.findDetailForPublic(seriesId))
                .thenReturn(sampleSeriesDetail)

            mvcRequest(HttpMethod.GET, "/series/{seriesId}", mapOf("seriesId" to seriesId))
                .andExpect {
                    status { is2xxSuccessful() }
                    jsonPath("$.series.id") { value("1") }
                    jsonPath("$.series.title") { value("시리즈 A") }
                    jsonPath("$.series.description") { value("시리즈 설명") }
                }
                .andDo { print() }
        }
    }
})
