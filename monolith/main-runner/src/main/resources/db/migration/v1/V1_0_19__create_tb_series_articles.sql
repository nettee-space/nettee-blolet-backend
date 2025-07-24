-- 스키마가 없다면 먼저 생성
CREATE SCHEMA IF NOT EXISTS "series";

-- Schema: series
CREATE TABLE IF NOT EXISTS "series"."series_article" (
    "id"                BIGSERIAL,
    "series_id"         BIGSERIAL,
    "article_id"        BIGINT, -- 비정규화
    "draft_id"          BIGINT,
    "display_order"     INTEGER,
    "created_at"        TIMESTAMP       DEFAULT NOW(),
    "updated_at"        TIMESTAMP
);

--테이블 코멘트
COMMENT ON TABLE "series"."series_article" IS '시리즈아티클';

-- 컬럼 코멘트
COMMENT ON COLUMN "series"."series_article"."id"                IS '시리즈아티클 PK';
COMMENT ON COLUMN "series"."series_article"."series_id"         IS '시리즈 PK';
COMMENT ON COLUMN "series"."series_article"."article_id"        IS '아티클 PK';
COMMENT ON COLUMN "series"."series_article"."draft_id"          IS '임시아티클 PK';
COMMENT ON COLUMN "series"."series_article"."display_order"     IS '사용자정렬순서';
COMMENT ON COLUMN "series"."series_article"."created_at"        IS '생성시간';
COMMENT ON COLUMN "series"."series_article"."updated_at"        IS '마지막 수정시간';

ALTER TABLE "series"."series_article" ADD CONSTRAINT "pk_series_article" PRIMARY KEY ("id");
ALTER TABLE "series"."series_article" ADD CONSTRAINT "uq_series_article_series_id_article_id" UNIQUE ("series_id", "article_id");
ALTER TABLE "series"."series_article" ADD CONSTRAINT "uq_series_article_series_id_draft_id" UNIQUE ("series_id", "draft_id");

CREATE INDEX IF NOT EXISTS idx_series_article_series_id ON series.series_article (series_id);