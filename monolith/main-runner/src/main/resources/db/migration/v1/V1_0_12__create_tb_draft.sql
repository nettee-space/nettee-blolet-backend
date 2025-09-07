CREATE SCHEMA IF NOT EXISTS "article";

CREATE TABLE IF NOT EXISTS article.draft (
    id                  BIGINT,
    blog_id             BIGINT,
    article_id          BIGINT,
    entry_block_id      BIGINT,
    banner_image_id     BIGINT,
    scheduled_deploy_at TIMESTAMP,

    title       VARCHAR(255),
    content     TEXT,
    -- content     JSONB               --JSON 검토중
    "path"      VARCHAR(255),

    status      INTEGER,
    created_at  TIMESTAMP   DEFAULT NOW()   NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NOW()   NOT NULL,

    CONSTRAINT "pk_draft" PRIMARY KEY ("id"),
    CONSTRAINT "uq_draft__blog_id__path" UNIQUE ("blog_id", "path")
);

-- 컬럼 코멘트
COMMENT ON COLUMN article.draft.id                  IS '임시 게시물 PK';
COMMENT ON COLUMN article.draft.blog_id             IS '블로그 ID';
COMMENT ON COLUMN article.draft.article_id          IS '포스트 ID';
COMMENT ON COLUMN article.draft.entry_block_id      IS '첫 블록 ID';
COMMENT ON COLUMN article.draft.banner_image_id     IS '대표 이미지 ID';
COMMENT ON COLUMN article.draft.scheduled_deploy_at IS '예약 배포일';
COMMENT ON COLUMN article.draft.title               IS '글 제목';
COMMENT ON COLUMN article.draft.content             IS '내용';
COMMENT ON COLUMN article.draft.status              IS '상태';
COMMENT ON COLUMN article.draft."path"              IS '게시물 URL path';
COMMENT ON COLUMN article.draft.created_at          IS '생성시간';
COMMENT ON COLUMN article.draft.updated_at          IS '마지막 수정시간';