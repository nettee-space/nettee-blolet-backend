CREATE SCHEMA IF NOT EXISTS "article";

CREATE TABLE IF NOT EXISTS article.hashtag (
    id          BIGSERIAL,
    name        VARCHAR(100),
    count       INTEGER DEFAULT 1,

    created_at  TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at  TIMESTAMP DEFAULT NOW() NOT NULL,

    CONSTRAINT pk_hashtag PRIMARY KEY (id),
    CONSTRAINT uq_hashtag__name UNIQUE (name)
);

-- 컬럼 코멘트
COMMENT ON COLUMN article.hashtag.id         IS '해시태그 PK';
COMMENT ON COLUMN article.hashtag.name       IS '해시태그 이름';
COMMENT ON COLUMN article.hashtag.count      IS '사용 횟수';
COMMENT ON COLUMN article.hashtag.created_at IS '생성시간';
COMMENT ON COLUMN article.hashtag.updated_at IS '수정시간';
