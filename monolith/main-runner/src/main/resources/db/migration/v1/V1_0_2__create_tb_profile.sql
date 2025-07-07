CREATE SCHEMA IF NOT EXISTS "profile";

CREATE TABLE IF NOT EXISTS "profile"."profile" (
    "id"          BIGINT                 NOT NULL,
    "user_id"     BIGINT,
    "nickname"    VARCHAR(255),
    "gender"      VARCHAR(255),
    "birth"       DATE,
    "profile_url" VARCHAR(255),
    "bio"         TEXT,
    "tel"         VARCHAR(255),
    "occupation"  VARCHAR(255),
    "interest"    VARCHAR(255),
    "created_at"  TIMESTAMP DEFAULT NOW() NOT NULL,
    "updated_at"  TIMESTAMP DEFAULT NOW() NOT NULL
);

COMMENT ON COLUMN "profile"."profile"."id"          IS '프로필 테이블의 PK';
COMMENT ON COLUMN "profile"."profile"."user_id"     IS '사용자 테이블 PK';
COMMENT ON COLUMN "profile"."profile"."nickname"    IS '사용자 닉네임';
COMMENT ON COLUMN "profile"."profile"."gender"      IS 'MALE(남성), FEMALE(여성)';
COMMENT ON COLUMN "profile"."profile"."birth"       IS '년, 월, 일 까지만 저장';
COMMENT ON COLUMN "profile"."profile"."profile_url" IS '프로필 이미지 url 주소';
COMMENT ON COLUMN "profile"."profile"."bio"         IS '사용자의 자기소개';
COMMENT ON COLUMN "profile"."profile"."tel"         IS '사용자의 연락처';
COMMENT ON COLUMN "profile"."profile"."occupation"  IS 'EMPTY(없음), QA, PRODUCT_MANAGER, DEVELOPER 등';
COMMENT ON COLUMN "profile"."profile"."interest"    IS 'EMPTY(없음), IT, STOCK(증권), FOOD(음식) 등';
COMMENT ON COLUMN "profile"."profile"."created_at"  IS '생성일자';
COMMENT ON COLUMN "profile"."profile"."updated_at"  IS '수정일자';

ALTER TABLE "profile"."profile" ADD CONSTRAINT "pk_profile" PRIMARY KEY ("id");

ALTER TABLE "profile"."profile" ADD CONSTRAINT "fk_profile_user"
    FOREIGN KEY ("user_id") REFERENCES "auth"."user" ("id");
