CREATE SCHEMA IF NOT EXISTS "article";

CREATE TABLE IF NOT EXISTS "article"."draft_block" (
    "id"          BIGINT,
    "blog_id"     BIGINT,
    "draft_id"    BIGINT,
    "article_id"  BIGINT,
    "next_block_id"  BIGINT,

    "type"        VARCHAR(255),
    "content"     TEXT,
    "style"       JSONB,

    "status"      VARCHAR(255),
    "create_at"   TIMESTAMP     DEFAULT NOW()   NOT NULL,
    "update_at"   TIMESTAMP     DEFAULT NOW()   NOT NULL
);

COMMENT ON COLUMN "article"."draft_block"."id"         IS '블록 테이블 PK';
COMMENT ON COLUMN "article"."draft_block"."blog_id"    IS '블로그테이블의 PK';
COMMENT ON COLUMN "article"."draft_block"."draft_id"   IS '임시아티클 테이블 PK';
COMMENT ON COLUMN "article"."draft_block"."article_id" IS '아티클테이블 PK';
COMMENT ON COLUMN "article"."draft_block"."next_block_id" IS '다음 블록 아이디';
COMMENT ON COLUMN "article"."draft_block"."type"       IS '블록 종류';
COMMENT ON COLUMN "article"."draft_block"."content"    IS '블록 내용';
COMMENT ON COLUMN "article"."draft_block"."style"      IS '블록별 스타일 및 속성 정보';
COMMENT ON COLUMN "article"."draft_block"."status"     IS '블록의 상태';
COMMENT ON COLUMN "article"."draft_block"."create_at"  IS '블록 생성일자';
COMMENT ON COLUMN "article"."draft_block"."update_at"  IS '블록 수정일자';

ALTER TABLE "article"."draft_block" ADD CONSTRAINT "pk_draft_block" PRIMARY KEY ("id");
