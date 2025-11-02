-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS profile.interest (
    id          BIGINT      NOT NULL,
    interest    VARCHAR     NULL,

    created_at	    TIMESTAMP		DEFAULT NOW()       NOT NULL,
    updated_at	    TIMESTAMP		DEFAULT NOW()       NOT NULL,

    CONSTRAINT pk_interest PRIMARY KEY (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE profile.interest IS '관심분야 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN profile.interest.id       IS '관심분야 PK';
COMMENT ON COLUMN profile.interest.interest IS '관심분야 명칭';
COMMENT ON COLUMN profile.interest.created_at          IS '생성일자';
COMMENT ON COLUMN profile.interest.updated_at          IS '수정일자';
