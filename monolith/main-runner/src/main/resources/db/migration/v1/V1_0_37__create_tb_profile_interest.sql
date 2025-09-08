-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS auth.profile_interest (
    profile_id      BIGINT      NOT NULL,
    interest_id     BIGINT      NOT NULL,

    CONSTRAINT pk_profile_interest PRIMARY KEY (profile_id, interest_id),

    -- FK 설정
    CONSTRAINT fk_profile_interest_profile FOREIGN KEY (profile_id) REFERENCES auth.profile (id),
    CONSTRAINT fk_profile_interest_interest FOREIGN KEY (interest_id) REFERENCES auth.interest (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE auth.profile_interest IS '프로필-관심사 매핑 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN auth.profile_interest.profile_id  IS '프로필 ID';
COMMENT ON COLUMN auth.profile_interest.interest_id IS '관심사 ID';
