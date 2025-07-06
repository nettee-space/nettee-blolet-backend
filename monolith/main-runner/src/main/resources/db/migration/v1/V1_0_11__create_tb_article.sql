CREATE SCHEMA IF NOT EXISTS article;

CREATE TABLE IF NOT EXISTS article.article (
    id          BIGINT,
    blog_id     BIGINT,

    title       VARCHAR(255),
    content     TEXT,
    -- content     JSONB               --JSON 검토중
    "path"      VARCHAR(255),

    total_views INTEGER,
    total_likes INTEGER,
    total_shares INTEGER,

    status      INTEGER,
    created_at  TIMESTAMP   DEFAULT NOW()   NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NOW()   NOT NULL
);

-- 컬럼 코멘트
COMMENT ON COLUMN article.article.id            IS 'Article PK';
COMMENT ON COLUMN article.article.blog_id       IS '블로그 ID';
COMMENT ON COLUMN article.article.title         IS '제목';
COMMENT ON COLUMN article.article.content       IS '본문';
COMMENT ON COLUMN article.article."path"        IS '게시물 URL path';
COMMENT ON COLUMN article.article.total_views   IS '총 조회수';
COMMENT ON COLUMN article.article.total_likes   IS '총 좋아요 수';
COMMENT ON COLUMN article.article.total_shares  IS '총 공유수';
COMMENT ON COLUMN article.article.status        IS '상태';
COMMENT ON COLUMN article.article.created_at    IS '생성시간';
COMMENT ON COLUMN article.article.updated_at    IS '마지막 수정시간';

ALTER TABLE "article"."article" ADD CONSTRAINT "pk_article" PRIMARY KEY ("id");
ALTER TABLE "article"."article" ADD CONSTRAINT "uq_article_path" UNIQUE ("path");