-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS auth.profile (
    id          BIGINT      NOT NULL,
    nickname    VARCHAR     NULL,
    job         VARCHAR     NULL,

    CONSTRAINT pk_profile PRIMARY KEY (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE auth.profile IS '프로필 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN auth.profile.id       IS '프로필 PK';
COMMENT ON COLUMN auth.profile.nickname IS '닉네임';
COMMENT ON COLUMN auth.profile.job      IS '직업';
