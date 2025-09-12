CREATE SCHEMA IF NOT EXISTS "profile";

-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS profile.profile (
    id          BIGINT      NOT NULL,
    nickname    VARCHAR     NULL,
    job         VARCHAR     NULL,
    user_id     BIGINT      NOT NULL,

    created_at	    TIMESTAMP		DEFAULT NOW()       NOT NULL,
    updated_at	    TIMESTAMP		DEFAULT NOW()       NOT NULL,

    CONSTRAINT pk_profile PRIMARY KEY (id),
    CONSTRAINT uq_profile_user UNIQUE (user_id),
    -- auth와 profile DB가 분리될 경우, 외래키 설정 불가
    CONSTRAINT fk_profile_user FOREIGN KEY (user_id) REFERENCES auth.user (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE profile.profile IS '프로필 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN profile.profile.id                  IS '프로필 PK';
COMMENT ON COLUMN profile.profile.nickname            IS '닉네임';
COMMENT ON COLUMN profile.profile.job                 IS '직업';
COMMENT ON COLUMN profile.profile.created_at          IS '생성일자';
COMMENT ON COLUMN profile.profile.updated_at          IS '수정일자';
