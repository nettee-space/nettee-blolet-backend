-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS auth.interest (
    id          BIGINT      NOT NULL,
    interest    VARCHAR     NULL,

    CONSTRAINT pk_interest PRIMARY KEY (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE auth.interest IS '관심분야 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN auth.interest.id       IS '관심분야 PK';
COMMENT ON COLUMN auth.interest.interest IS '관심분야 명칭';
