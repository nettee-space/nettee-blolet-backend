CREATE SCHEMA IF NOT EXISTS "blog";

CREATE TABLE IF NOT EXISTS "blog"."blog" (
    "id"            BIGINT,
    "user_id"       BIGINT,
    "profile_id"    BIGINT,
    "username"      VARCHAR(255),
    "nickname"      VARCHAR(255),
    "name"          VARCHAR(255),
    "url_identifier" VARCHAR(255),
    "created_at"    TIMESTAMP   DEFAULT NOW()  NOT NULL,
    "updated_at"    TIMESTAMP   DEFAULT NOW()  NOT NULL,

    CONSTRAINT "pk_blog" PRIMARY KEY ("id"),
    CONSTRAINT "uq_blog_url_identifier" UNIQUE ("url_identifier")
);

COMMENT ON COLUMN "blog"."blog"."id"            IS '블로그 테이블 PK';
COMMENT ON COLUMN "blog"."blog"."user_id"       IS '사용자 테이블 PK';
COMMENT ON COLUMN "blog"."blog"."profile_id"    IS '프로필 테이블 PK';
COMMENT ON COLUMN "blog"."blog"."username"      IS '계정 고유 아이디';
COMMENT ON COLUMN "blog"."blog"."nickname"      IS '프로필 이름';
COMMENT ON COLUMN "blog"."blog"."name"          IS '사용자 블로그명';
COMMENT ON COLUMN "blog"."blog"."url_identifier" IS '사용자 블로그 URL 주소';
COMMENT ON COLUMN "blog"."blog"."created_at"    IS '생성일자';
COMMENT ON COLUMN "blog"."blog"."updated_at"    IS '수정일자';
