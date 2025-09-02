package nettee.blolet.article.api.validation;

import nettee.blolet.article.api.validation.context.DraftContextValidationSupplier;
import nettee.common.validation.model.RegExpFlagBuilder;
import nettee.common.validation.model.RegExpFlagBuilder.RegexpFlag;
import nettee.common.validation.model.StringValidationProperty;
import nettee.common.validation.model.ValidationMapBuilder;
import nettee.common.validation.model.ValidationResponseModel;
import nettee.common.validation.model.interfaces.BaseValidationProperty;
import nettee.common.validation.model.interfaces.RegexpSpec;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

import static nettee.blolet.article.api.validation.StaticDraftValidationSupplier.DraftValidationContexts.DRAFT_CONTEXT_CREATE;
import static nettee.blolet.article.api.validation.StaticDraftValidationSupplier.DraftValidationContexts.DRAFT_CONTEXT_PATCH;
import static nettee.blolet.article.api.validation.StaticDraftValidationSupplier.TargetFields.DRAFT_BLOG_ID;
import static nettee.blolet.article.api.validation.StaticDraftValidationSupplier.TargetFields.DRAFT_ENTRY_BLOCK_ID;
import static nettee.blolet.article.api.validation.StaticDraftValidationSupplier.TargetFields.DRAFT_PATH;
import static nettee.blolet.article.api.validation.StaticDraftValidationSupplier.TargetFields.DRAFT_TITLE;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MAX_LENGTH;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.MIN_LENGTH;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.REGEXP;
import static nettee.common.validation.model.interfaces.BaseValidationProperty.ReservedFieldNames.REQUIRED;

// NOTE: 정책의 DB 의존성 등 동적인 갱신 필요성 여부에 따른 아키텍처
//  도메인중심적으로 정책 제공 시(재배포를 통해서만 제공 시) API 모듈이 가장 적합해 보이고,
//  DB 의존 시 application(port)-adapter 구조가 적합할 수도 있음.
//  (DB에서 직접 조회해서 주는 것이 아니더라도, DB 갱신 시 객체가 새 정책을 주입받을 수 있어야 함.)
/**
 * 임시 글(Draft)의 작성(create) 및 수정(patch) 시 필요한 유효성 검증 규칙을
 * 서버에서 정적으로 제공하는 컴포넌트입니다.
 * <ul>
 *     <li>{@code create} 컨텍스트: 신규 임시 글 생성 시 유효성 규칙</li>
 *     <li>{@code patch} 컨텍스트: 기존 임시 글 수정 시 유효성 규칙</li>
 * </ul>
 *
 * <br />모든 ValidationProperty는 불변 맵(unmodifiable map)으로 제공합니다.
 * {@link #get(String)} 메서드 호출 시 context 값에 따라 올바른 규칙 집합을 반환합니다.
 * <br />지원하지 않는 context를 입력하면 {@link AssertionError} 또는 {@link Error}를 발생시킵니다.
 *
 * @author merge-simpson
 * @since 2025-08-23
 */
@Component
public final class StaticDraftValidationSupplier implements DraftContextValidationSupplier {

    private static final Map<String, BaseValidationProperty> DRAFT_CREATE_VALIDATION;
    private static final Map<String, BaseValidationProperty> DRAFT_PATCH_VALIDATION;

    private static final int TITLE_MIN_LENGTH = 3;
    private static final int TITLE_MAX_LENGTH = 100;
    private static final int PATH_MAX_LENGTH = 2000; // cuz' 레거시 호환성: 2083글자
    private static final RegexpSpec PATH_REGEXP = RegexpSpec.builder()
            .pattern("^[\\p{L}\\p{N}\\p{M}\\p{S}](?:[\\p{L}\\p{N}\\p{M}\\p{S}_-]*[\\p{L}\\p{N}\\p{M}\\p{S}])?$")
            .flags(
                    RegExpFlagBuilder.builder()
                            .add(RegexpFlag.U)
                            .build()
            )
            .build();

    /**
     * 유효성을 마지막으로 수정한 시각(고정값)입니다.
     */
    private static final Instant LAST_UPDATED_AT = Instant.parse("2025-08-23T00:00:00.000Z");

    static {
        // generate validation properties
        var blogIdValidation = StringValidationProperty.builder()
                .required(true)
                .messages(Map.of(REQUIRED, "블로그 식별 값이 필요합니다. 문제가 지속되면 문의하시기 바랍니다."))
                .build();
        var titleCreateValidation = StringValidationProperty.builder()
                .minLength(TITLE_MIN_LENGTH)
                .maxLength(TITLE_MAX_LENGTH)
                .messages(Map.of(
                        MIN_LENGTH, "제목은 " + TITLE_MIN_LENGTH + "글자 이상이어야 합니다.",
                        MAX_LENGTH, "제목은 최대 " + TITLE_MAX_LENGTH + "글자 이하로 입력하여야 합니다."
                ))
                .build();
        var titlePatchValidation = StringValidationProperty.builder()
                .required(true)
                .minLength(TITLE_MIN_LENGTH)
                .maxLength(TITLE_MAX_LENGTH)
                .messages(Map.of(
                        REQUIRED, "제목을 입력하세요.",
                        MIN_LENGTH, "제목은 " + TITLE_MIN_LENGTH + "글자 이상이어야 합니다.",
                        MAX_LENGTH, "제목은 최대 " + TITLE_MAX_LENGTH + "글자 이하로 입력하여야 합니다."
                ))
                .build();
        var pathCreateValidation = StringValidationProperty.builder()
                .maxLength(PATH_MAX_LENGTH)
                .regexp(PATH_REGEXP)
                .messages(Map.of(
                        MAX_LENGTH, "게시물 URL 경로의 최대 길이는 " + PATH_MAX_LENGTH + " 글자입니다.",
                        REGEXP, "게시물 URL은 문자(한글·영문 등), 숫자, 기호(이모지 포함), 밑줄(_)과 하이픈(-)을 포함할 수 있습니다. "
                                + "단, 시작과 끝에는 밑줄(_)이나 하이픈(-)이 올 수 없습니다."
                ))
                .build();
        var pathPatchValidation = StringValidationProperty.builder()
                .required(true)
                .maxLength(PATH_MAX_LENGTH)
                .regexp(PATH_REGEXP)
                .messages(Map.of(
                        REQUIRED, "게시물의 URL 경로를 입력하세요.",
                        MAX_LENGTH, "게시물 URL 경로의 최대 길이는 " + PATH_MAX_LENGTH + " 글자입니다.",
                        REGEXP, "게시물 URL은 각국 언어, 각국 숫자, 기호(이모지 포함) 및 밑줄(_)과 하이픈(-)을 포함할 수 있습니다. "
                                + "단, 시작과 끝에는 밑줄(_)이나 하이픈(-)이 올 수 없습니다."
                ))
                .build();
        var entryBlockIdPatchValidation = StringValidationProperty.builder()
                .required(true)
                .messages(Map.of(REQUIRED, "첫 블록 아이디가 반드시 필요합니다. 문제가 지속되면 문의해 주세요."))
                .build();

        // as unmodifiable maps
        DRAFT_CREATE_VALIDATION = ValidationMapBuilder.builder()
                .put(DRAFT_BLOG_ID, blogIdValidation)
                .put(DRAFT_TITLE, titleCreateValidation)
                .put(DRAFT_PATH, pathCreateValidation)
                .build();
        DRAFT_PATCH_VALIDATION = ValidationMapBuilder.builder()
                .put(DRAFT_BLOG_ID, blogIdValidation)
                .put(DRAFT_TITLE, titlePatchValidation)
                .put(DRAFT_PATH, pathPatchValidation)
                .put(DRAFT_ENTRY_BLOCK_ID, entryBlockIdPatchValidation)
                .build();
    }

    /**
     * Returns the validation response model based on the given context.
     *
     * @param context must be one of ("create") or ("patch").
     *                Can use {@link DraftValidationContexts#DRAFT_CONTEXT_CREATE}
     *                or {@link DraftValidationContexts#DRAFT_CONTEXT_PATCH}
     * @return validation response model containing validation rules and metadata
     * @throws AssertionError if context is not supported
     */
    @Override
    public ValidationResponseModel get(String context) {
        assert Set.of(DRAFT_CONTEXT_CREATE, DRAFT_CONTEXT_PATCH).contains(context)
                : "Unsupported context: " + context + ". Supported values are 'create' or 'patch'. \n"
                + "지원하지 않는 context 값입니다: " + context + ". 지원하는 값은 'create' 또는 'patch' 입니다.";

        return switch (context) {
            case DRAFT_CONTEXT_CREATE -> getCreateValidationModel();
            case DRAFT_CONTEXT_PATCH -> getPatchValidationModel();
            default -> throw new Error(
                    "Supported values are 'create' or 'patch'. \n"
                            + "지원하지 않는 context 값입니다. 지원하는 값은 'create' 또는 'patch' 입니다. \n"
                            + "문제 지속 시, 문의는 뽀도가 먹꼬 찌푼뎅."
            );
        };
    }

    /**
     * Returns the validation rules for draft creation.
     *
     * @return validation response model for {@code create} context
     */
    @Override
    public ValidationResponseModel getCreateValidationModel() {
        return ValidationResponseModel.builder()
                .validations(DRAFT_CREATE_VALIDATION)
                .lastUpdatedAt(LAST_UPDATED_AT)
                .serverTime(Instant.now())
                .build();
    }

    /**
     * Returns the validation rules for draft patching.
     *
     * @return validation response model for {@code patch} context
     */
    @Override
    public ValidationResponseModel getPatchValidationModel() {
        return ValidationResponseModel.builder()
                .validations(DRAFT_PATCH_VALIDATION)
                .lastUpdatedAt(LAST_UPDATED_AT)
                .serverTime(Instant.now())
                .build();
    }

    /**
     * Defines supported draft validation contexts.
     * <p>지원하는 context 상수 모음: {@code create}, {@code patch}.
     */
    public static final class DraftValidationContexts {
        public static final String DRAFT_CONTEXT_CREATE = "create";
        public static final String DRAFT_CONTEXT_PATCH = "patch";
    }

    /**
     * Defines field names used in draft validation.
     * <p>유효성 검증 대상 필드명 상수 모음:
     * {@code blogId}, {@code title}, {@code path}, {@code entryBlockId}.
     */
    static final class TargetFields {
        static final String DRAFT_BLOG_ID = "blogId";
        static final String DRAFT_TITLE = "title";
        static final String DRAFT_PATH = "path";
        static final String DRAFT_ENTRY_BLOCK_ID = "entryBlockId";
    }
}