CREATE SCHEMA IF NOT EXISTS "auth";

CREATE TABLE IF NOT EXISTS "auth"."password_count" (
    "hash_password" VARCHAR(255) NOT NULL,
    "used_count"    BIGINT       NOT NULL
);

COMMENT ON COLUMN "auth"."password_count"."hash_password" IS '해시 비밀번호';
COMMENT ON COLUMN "auth"."password_count"."used_count"    IS '동일한 비밀번호 사용 횟수';

ALTER TABLE "auth"."password_count" ADD CONSTRAINT "pk_password_count" PRIMARY KEY ("hash_password");
