CREATE SCHEMA IF NOT EXISTS "image";

CREATE TABLE IF NOT EXISTS image.image_usage (
    id              BIGINT,
    rel_id          BIGINT,
    "sequence"      INTEGER,
    url             TEXT,
    target_table    VARCHAR(255),
    created_at      TIMESTAMP       DEFAULT NOW()   NOT NULL,
    updated_at      TIMESTAMP       DEFAULT NOW()   NOT NULL,

    CONSTRAINT "pk_image_usage" PRIMARY KEY ("id"),
    CONSTRAINT "uq_image_usage__url" UNIQUE ("url"),
    CONSTRAINT "uq_image_usage__rel_id__sequence" UNIQUE ("rel_id", "sequence")
);

CREATE TABLE IF NOT EXISTS image.image_usage_seq (
    id          BIGINT,
    rel_id      BIGINT,
    last_seq    INTEGER,

    created_at  TIMESTAMP   DEFAULT NOW()   NOT NULL,
    updated_at  TIMESTAMP   DEFAULT NOW()   NOT NULL,

    CONSTRAINT "pk_image_usage_seq" PRIMARY KEY ("rel_id")
);

-- image_usage 테이블 컬럼 코멘트
COMMENT ON COLUMN image.image_usage.id              IS '이미지 사용 내역 PK';
COMMENT ON COLUMN image.image_usage.rel_id          IS '연결 ID';
COMMENT ON COLUMN image.image_usage."sequence"      IS '이미지 시퀀스';
COMMENT ON COLUMN image.image_usage.url             IS '이미지 URL';
COMMENT ON COLUMN image.image_usage.target_table    IS '이미지가 사용되는 테이블';
COMMENT ON COLUMN image.image_usage.created_at      IS '등록 시간';
COMMENT ON COLUMN image.image_usage.updated_at      IS '수정 시간';

-- image_usage_seq 테이블 컬럼 코멘트
COMMENT ON COLUMN image.image_usage_seq.id          IS '이미지 사용내역 시퀀스 PK';
COMMENT ON COLUMN image.image_usage_seq.rel_id      IS '연결 ID';
COMMENT ON COLUMN image.image_usage_seq.last_seq    IS '마지막 이미지 시퀀스';
COMMENT ON COLUMN image.image_usage_seq.created_at  IS '등록 시간';
COMMENT ON COLUMN image.image_usage_seq.updated_at  IS '수정 시간';