CREATE SCHEMA IF NOT EXISTS "article";

CREATE TABLE IF NOT EXISTS article.article_hashtag (
    id          BIGINT,
    draft_id    BIGINT,
    hashtag_id  BIGINT,

    status      INTEGER,
    created_at  TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at  TIMESTAMP DEFAULT NOW() NOT NULL,

    CONSTRAINT pk_article_hashtag PRIMARY KEY ("id"),
    CONSTRAINT uq_article_hashtag__draft_id__hashtag_id UNIQUE ("draft_id", "hashtag_id")
);

-- 컬럼 코멘트
COMMENT ON COLUMN article.article_hashtag.id         IS '게시글-해시태그 매핑 PK';
COMMENT ON COLUMN article.article_hashtag.draft_id   IS '연결된 드래프트 ID';
COMMENT ON COLUMN article.article_hashtag.hashtag_id IS '연결된 해시태그 ID';
COMMENT ON COLUMN article.article_hashtag.status     IS '상태';
COMMENT ON COLUMN article.article_hashtag.created_at IS '생성시간';
COMMENT ON COLUMN article.article_hashtag.updated_at IS '수정시간';
