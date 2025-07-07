CREATE SCHEMA IF NOT EXISTS "auth";

CREATE TABLE IF NOT EXISTS "auth"."user_auth" (
    "user_id"       BIGINT       NOT NULL,
    "hash_password" VARCHAR(255) NOT NULL,
    "salt"          VARCHAR(255) NOT NULL,
    "updated_at"    TIMESTAMP
);

COMMENT ON COLUMN "auth"."user_auth"."user_id"        IS '사용자 테이블 PK';
COMMENT ON COLUMN "auth"."user_auth"."hash_password"  IS '해시 비밀번호';
COMMENT ON COLUMN "auth"."user_auth"."salt"           IS '가변 솔트';
COMMENT ON COLUMN "auth"."user_auth"."updated_at"     IS '비밀번호 변경 시각';

ALTER TABLE "auth"."user_auth" ADD CONSTRAINT "pk_user_auth" PRIMARY KEY ("user_id");

ALTER TABLE "auth"."user_auth" ADD CONSTRAINT "fk_user_auth_user"
    FOREIGN KEY ("user_id") REFERENCES "auth"."user" ("id");
