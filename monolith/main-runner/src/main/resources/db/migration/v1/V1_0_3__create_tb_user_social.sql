CREATE SCHEMA IF NOT EXISTS "auth";

CREATE TABLE IF NOT EXISTS "auth"."user_social" (
    "id"                BIGINT               NOT NULL,
    "user_id"           BIGINT               NOT NULL,
    "provider"          VARCHAR(255),
    "provider_user_id"  VARCHAR(255),
    "created_at"        TIMESTAMP DEFAULT NOW() NOT NULL,
    "updated_at"        TIMESTAMP DEFAULT NOW() NOT NULL
);

COMMENT ON COLUMN "auth"."user_social"."id"                IS '소셜 ID';
COMMENT ON COLUMN "auth"."user_social"."user_id"           IS '사용자 테이블 PK';
COMMENT ON COLUMN "auth"."user_social"."provider"          IS '소셜 로그인 제공자 (GOOGLE, KAKAO 등)';
COMMENT ON COLUMN "auth"."user_social"."provider_user_id"  IS '소셜 내 사용자 고유 식별자';
COMMENT ON COLUMN "auth"."user_social"."created_at"        IS '생성일자';
COMMENT ON COLUMN "auth"."user_social"."updated_at"        IS '수정일자';

ALTER TABLE "auth"."user_social" ADD CONSTRAINT "pk_user_social" PRIMARY KEY ("id");

ALTER TABLE "auth"."user_social" ADD CONSTRAINT "fk_user_social_user"
    FOREIGN KEY ("user_id") REFERENCES "auth"."user" ("id");
