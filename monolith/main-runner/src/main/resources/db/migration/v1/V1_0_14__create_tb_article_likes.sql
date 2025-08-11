CREATE SCHEMA IF NOT EXISTS "article";

CREATE TABLE IF NOT EXISTS "article"."article_likes" (
    "id"           BIGINT,
    "user_id"      BIGINT,
    "profile_id"   BIGINT,
    "article_id"   BIGINT,

    "count"        INTEGER,

    "status"       INTEGER,
    "created_at"   TIMESTAMP      DEFAULT NOW()   NOT NULL,
    "updated_at"   TIMESTAMP      DEFAULT NOW()   NOT NULL,

    CONSTRAINT "pk_article_likes" PRIMARY KEY ("id"),
    CONSTRAINT "uq_article_likes_profileid_articleid" UNIQUE ("article_id", "profile_id")
);

COMMENT ON COLUMN "article"."article_likes"."id"         IS '블록 테이블 PK';
COMMENT ON COLUMN "article"."article_likes"."user_id"    IS '유저 테이블의 PK';
COMMENT ON COLUMN "article"."article_likes"."profile_id" IS '프로필 테이블의 PK';
COMMENT ON COLUMN "article"."article_likes"."article_id" IS '아티클테이블 PK';

COMMENT ON COLUMN "article"."article_likes"."count"      IS '한 사용자가 누른 좋아요 수';

COMMENT ON COLUMN "article"."article_likes"."created_at"  IS '좋아요 생성일자';
COMMENT ON COLUMN "article"."article_likes"."updated_at"  IS '좋아요 수정일자';
