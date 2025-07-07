CREATE SCHEMA IF NOT EXISTS "auth";

CREATE TABLE IF NOT EXISTS "auth"."user_auth_history" (
    "id"            BIGINT       NOT NULL,
    "user_id"       BIGINT       NOT NULL,
    "hash_password" VARCHAR(255) NOT NULL,
    "salt"          VARCHAR(255) NOT NULL,
    "updated_at"    TIMESTAMP
);

COMMENT ON COLUMN "auth"."user_auth_history"."id"            IS '히스토리 ID';
COMMENT ON COLUMN "auth"."user_auth_history"."user_id"       IS '사용자 테이블 PK';
COMMENT ON COLUMN "auth"."user_auth_history"."hash_password" IS '과거 해시 비밀번호';
COMMENT ON COLUMN "auth"."user_auth_history"."salt"          IS '고정 솔트';
COMMENT ON COLUMN "auth"."user_auth_history"."updated_at"    IS '비밀번호 변경 시각';

ALTER TABLE "auth"."user_auth_history" ADD CONSTRAINT "pk_user_auth_history" PRIMARY KEY ("id", "user_id");

ALTER TABLE "auth"."user_auth_history" ADD CONSTRAINT "fk_user_auth_history_user"
    FOREIGN KEY ("user_id") REFERENCES "auth"."user" ("id");
