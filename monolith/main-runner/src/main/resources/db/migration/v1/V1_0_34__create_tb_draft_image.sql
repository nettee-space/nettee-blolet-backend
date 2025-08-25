CREATE TABLE IF NOT EXISTS article.draft_image (
    id          BIGINT,
    image_url   TEXT        NOT NULL,
    created_at  TIMESTAMP   DEFAULT NOW()   NOT NULL
);

-- 컬럼 코멘트
COMMENT ON COLUMN article.draft_image.id        IS '이미지 PK';
COMMENT ON COLUMN article.draft_image.image_url IS '이미지 URL';
COMMENT ON COLUMN article.draft_image.created_at IS '등록 시간';

ALTER TABLE "article"."draft_image" ADD CONSTRAINT "pk_draft_image" PRIMARY KEY ("id");
