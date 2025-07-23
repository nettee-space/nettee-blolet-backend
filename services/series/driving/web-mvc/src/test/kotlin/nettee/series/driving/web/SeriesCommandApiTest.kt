package nettee.series.driving.web

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.FreeSpec
import nettee.series.application.usecase.SeriesCreateUseCase
import nettee.series.application.usecase.SeriesDeleteUseCase
import nettee.series.application.usecase.SeriesUpdateUseCase
import nettee.series.domain.Series
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesUpdateCommand
import nettee.series.driving.web.dto.SeriesCommandDto.SeriesCreateCommand
import nettee.series.driving.web.mapper.SeriesDtoMapper
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.request

@WebMvcTest(SeriesCommandApi::class)
class SeriesCommandApiTest(
    @Autowired private val mvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
    @MockitoBean private val seriesCreateUseCase: SeriesCreateUseCase,
    @MockitoBean private val seriesUpdateUseCase: SeriesUpdateUseCase,
    @MockitoBean private val seriesDeleteUseCase: SeriesDeleteUseCase,
    @MockitoBean private val seriesDtoMapper: SeriesDtoMapper
) : FreeSpec({

    // given
    val seriesDomain = Series.builder().id("1").blogId("1").title("시리즈 테스트 제목").description("시리즈 예시").build()
    val mvcRequest: (HttpMethod, String, Map<String, Any>, Any?) -> ResultActionsDsl =
        { method, url, pathVariables, requestBody ->
            mvc.request(method, url, *pathVariables.values.toTypedArray()) {
                contentType = MediaType.APPLICATION_JSON
                if (requestBody != null) {
                    content = objectMapper.writeValueAsString(requestBody)
                }
            }
        }

    "[POST] 시리즈 생성 요청" - {
        // given
        val seriesCreateResponse = seriesDomain

        "[정상 요청] 제목이 30자 내외이며 존재할 때" - {
            // when
            val createCommand = SeriesCreateCommand("시리즈 테스트 제목", "시리즈 예시", null, null)

            "2xx 응답 상태 반환" {
                // then
                mvcRequest(
                    HttpMethod.POST,
                    "/series/{blogId}",
                    mapOf("blogId" to seriesCreateResponse.blogId),
                    createCommand
                )
                    .andExpect {
                        status { is2xxSuccessful() }
                        jsonPath("series.id") { value(seriesCreateResponse.id) }
                        jsonPath("series.title") { value(seriesCreateResponse.title) }
                        jsonPath("series.description") { value(seriesCreateResponse.description) }
                    }
                    .andDo { print() }
                    .andReturn()
            }
        }

        "[실패 요청] 시리즈 제목이 공백 혹은 없을 때" - {
            // when
            val failBlankTitleCommand = SeriesCreateCommand("", "시리즈 예시", null, null)

            // then
            "제목 공백 4xx 응답 상태 반환" {
                mvcRequest(
                    HttpMethod.POST,
                    "/series/{blogId}",
                    mapOf("blogId" to seriesCreateResponse.blogId),
                    failBlankTitleCommand
                )
                    .andExpect {
                        status { is4xxClientError() }
                    }
                    .andDo { print() }
                    .andReturn()
            }

            // when
            val failNullTitleCommand = SeriesCreateCommand(null, "시리즈 예시", null, null)

            "제목 없을 때 4xx 응답 상태 반환" {
                mvcRequest(
                    HttpMethod.POST,
                    "/series/{blogId}",
                    mapOf("blogId" to seriesCreateResponse.blogId),
                    failNullTitleCommand
                )
                    .andExpect {
                        status { is4xxClientError() }
                    }
                    .andDo { print() }
                    .andReturn()
            }
        }
    }

    "[PUT] 시리즈 수정 요청" - {
        // given
        val seriesUpdateResponse = seriesDomain

        "[정상 요청] 제목이 30자 내외이며 존재할 때" - {
            // when
            val updateCommand = SeriesUpdateCommand("1", "시리즈 테스트 제목", "시리즈 예시", null, null)

            "2xx 정상 상태 반환" {
                // then
                mvcRequest(
                    HttpMethod.PUT,
                    "/series",
                    emptyMap(),
                    updateCommand
                )
                    .andExpect {
                        status { is2xxSuccessful() }
                        jsonPath("series.id") { value(seriesUpdateResponse.id) }
                        jsonPath("series.title") { value(seriesUpdateResponse.title) }
                        jsonPath("series.description") { value(seriesUpdateResponse.description) }
                    }
                    .andDo { print() }
                    .andReturn()
            }
        }

        "[실패 요청] 제목이 공백 일 때" - {
            // when
            val failBlankTitleCommand = SeriesUpdateCommand("1", "", "시리즈 예시", null, null)

            // then
            "제목 공백 4xx 응답 상태 반환" {
                mvcRequest(
                    HttpMethod.PUT,
                    "/series/{seriesId}",
                    mapOf("seriesId" to seriesUpdateResponse.id),
                    failBlankTitleCommand
                )
                    .andExpect {
                        status { is4xxClientError() }
                    }
                    .andDo { print() }
                    .andReturn()
            }

            // when
            val failNullTitleCommand = SeriesUpdateCommand("1", null, "시리즈 예시", null, null)

            "제목 없을 때 4xx 응답 상태 반환" {
                mvcRequest(
                    HttpMethod.PUT,
                    "/series/{seriesId}",
                    mapOf("seriesId" to seriesUpdateResponse.id),
                    failNullTitleCommand
                )
                    .andExpect {
                        status { is4xxClientError() }
                    }
                    .andDo { print() }
                    .andReturn()
            }
        }
    }

    "[DELETE] 시리즈 삭제 요청" - {
        "[정상 요청] 블로그에 존재하는 시리즈 일 때" - {
            "2xx 응답 상태 반환" {
                mvcRequest(HttpMethod.DELETE, "/series/{id}", mapOf("id" to seriesDomain.id), null)
                    .andExpect {
                        status { is2xxSuccessful() }
                    }
            }
        }
    }

    beforeSpec {
        `when`(seriesDtoMapper.toDomain(any())).thenReturn(seriesDomain)
        `when`(seriesCreateUseCase.createSeries(any())).thenReturn(seriesDomain)
        `when`(seriesUpdateUseCase.updateSeries(any())).thenReturn(seriesDomain)
        doNothing().`when`(seriesDeleteUseCase).deleteSeries(any())
    }
})
