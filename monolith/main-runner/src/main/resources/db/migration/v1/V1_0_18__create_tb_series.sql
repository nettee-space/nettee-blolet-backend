-- 스키마가 없다면 먼저 생성
CREATE SCHEMA IF NOT EXISTS "series";

-- Schema: series
CREATE TABLE IF NOT EXISTS "series"."series" (
    "id"          BIGINT,
    "blog_id"     BIGINT,
    "title"       VARCHAR(255),
    "user_sort_order"  INTEGER,
    "created_at"  TIMESTAMP       DEFAULT NOW(),
    "updated_at"  TIMESTAMP
    );

--테이블 코멘트
COMMENT ON TABLE "series"."series" IS '시리즈';

-- 컬럼 코멘트
COMMENT ON COLUMN "series"."series"."title"               IS '시리즈제목';
COMMENT ON COLUMN "series"."series"."user_sort_order"     IS '사용자정렬순서';
COMMENT ON COLUMN "series"."series"."created_at"          IS '생성시간';
COMMENT ON COLUMN "series"."series"."updated_at"          IS '마지막 수정시간';

ALTER TABLE "series"."series" ADD CONSTRAINT "pk_series" PRIMARY KEY ("id");