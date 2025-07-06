CREATE SCHEMA IF NOT EXISTS "article";

CREATE TABLE IF NOT EXISTS article.draft (
    id          BIGINT,
    blog_id     BIGINT,
    article_id  BIGINT,

    title       VARCHAR(255),
    content     TEXT,
    -- content     JSONB               --JSON 검토중

    status      INTEGER,
    created_at  TIMESTAMP   DEFAULT NOW()   NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NOW()   NOT NULL,

    );

-- 컬럼 코멘트
COMMENT ON COLUMN article.draft.title       IS '글 제목';
COMMENT ON COLUMN article.draft.content     IS '내용';
COMMENT ON COLUMN article.draft.status      IS '상태';
COMMENT ON COLUMN article.draft.created_at  IS '생성시간';
COMMENT ON COLUMN article.draft.updated_at  IS '마지막 수정시간';
COMMENT ON COLUMN article.draft.blog_id     IS '블로그 ID';
COMMENT ON COLUMN article.draft.article_id  IS '포스트 ID';

ALTER TABLE "article"."draft" ADD CONSTRAINT "pk_draft" PRIMARY KEY ("id");