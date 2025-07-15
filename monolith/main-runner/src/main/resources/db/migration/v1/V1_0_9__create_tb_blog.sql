CREATE SCHEMA IF NOT EXISTS "blog";

CREATE TABLE IF NOT EXISTS "blog"."blog" (
    "id"         BIGINT,
    "user_id"    BIGINT,
    "name"       VARCHAR(255),
    "url"        VARCHAR(255),
    "created_at" TIMESTAMP   DEFAULT NOW()  NOT NULL,
    "updated_at" TIMESTAMP   DEFAULT NOW()  NOT NULL
);

COMMENT ON COLUMN "blog"."blog"."id"         IS '블로그테이블의 PK';
COMMENT ON COLUMN "blog"."blog"."user_id"    IS '사용자테이블 PK';
COMMENT ON COLUMN "blog"."blog"."name"       IS '사용자 블로그명';
COMMENT ON COLUMN "blog"."blog"."url"        IS '사용자 블로그 URL 주소';
COMMENT ON COLUMN "blog"."blog"."created_at" IS '생성일자';
COMMENT ON COLUMN "blog"."blog"."updated_at" IS '수정일자';

ALTER TABLE "blog"."blog" ADD CONSTRAINT "pk_blog" PRIMARY KEY ("id");
ALTER TABLE "blog"."blog" ADD CONSTRAINT "uq_blog_url" UNIQUE ("url");
