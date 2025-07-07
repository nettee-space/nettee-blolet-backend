-- 스키마가 없다면 먼저 생성
CREATE SCHEMA IF NOT EXISTS "series";

-- Schema: series
CREATE TABLE IF NOT EXISTS "series"."series_article" (
    "series_id"         BIGINT,
    "draft_id"          BIGINT,
    "display_order"     INTEGER,
    "created_at"        TIMESTAMP       DEFAULT NOW(),
    "updated_at"        TIMESTAMP
);

--테이블 코멘트
COMMENT ON TABLE "series"."series_articles" IS '시리즈아티클';

-- 컬럼 코멘트
COMMENT ON COLUMN "series"."series_article"."display_order"    IS '사용자정렬순서';
COMMENT ON COLUMN "series"."series_article"."created_at"       IS '생성시간';
COMMENT ON COLUMN "series"."series_article"."updated_at"       IS '마지막 수정시간';

ALTER TABLE "series"."series_articles" ADD CONSTRAINT "uq_series_articles_series_id" UNIQUE ("series_id");
ALTER TABLE "series"."series_articles" ADD CONSTRAINT "uq_series_articles_draft_id" UNIQUE ("draft_id");