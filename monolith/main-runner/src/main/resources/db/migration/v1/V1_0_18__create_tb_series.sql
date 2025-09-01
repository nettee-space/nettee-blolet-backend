CREATE TABLE IF NOT EXISTS "article"."series" (
    "id"          BIGSERIAL,
    "blog_id"     BIGINT,
    "title"       VARCHAR(255),
    "description" VARCHAR(255),
    "banner_url"  VARCHAR(255),
    "display_order" INTEGER,
    "created_at"  TIMESTAMP       DEFAULT NOW(),
    "updated_at"  TIMESTAMP
);

--테이블 코멘트
COMMENT ON TABLE "article"."series" IS '시리즈';

-- 컬럼 코멘트
COMMENT ON COLUMN "article"."series"."id"                IS '시리즈 PK';
COMMENT ON COLUMN "article"."series"."blog_id"           IS '블로그 PK';
COMMENT ON COLUMN "article"."series"."title"             IS '시리즈 제목';
COMMENT ON COLUMN "article"."series"."description"       IS '시리즈 설명';
COMMENT ON COLUMN "article"."series"."banner_url"        IS '시리즈 배너 이미지';
COMMENT ON COLUMN "article"."series"."display_order"     IS '사용자 정렬 순서';
COMMENT ON COLUMN "article"."series"."created_at"        IS '생성시간';
COMMENT ON COLUMN "article"."series"."updated_at"        IS '마지막 수정 시간';

ALTER TABLE "article"."series" ADD CONSTRAINT "pk_series" PRIMARY KEY ("id");
ALTER TABLE "article"."series" ADD CONSTRAINT "uq_series_blog_id_title" UNIQUE ("blog_id", "title");