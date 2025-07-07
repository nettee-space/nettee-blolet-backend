-- 1. 테이블 생성: 답글 신고
CREATE TABLE IF NOT EXISTS reply_report (
    id        BIGINT    NOT NULL,  -- 답글 신고 PK
    title     TEXT      NULL,      -- 제목
    content   TEXT      NULL,      -- 내용
    reply_id  BIGINT    NULL,      -- 답글을 식별하기 위한 ID
    user_id   BIGINT    NULL,      -- 사용자 테이블 PK

    CONSTRAINT pk_reply_report PRIMARY KEY (id)
);

-- 2. 테이블 설명: 답글 신고
COMMENT ON TABLE reply_report IS '답글 신고 테이블';

-- 3. 컬럼 설명: 답글 신고
COMMENT ON COLUMN reply_report.id       IS '답글 신고 ID';
COMMENT ON COLUMN reply_report.title    IS '제목';
COMMENT ON COLUMN reply_report.content  IS '내용';
COMMENT ON COLUMN reply_report.reply_id IS '답글 ID';
COMMENT ON COLUMN reply_report.user_id  IS '사용자 ID';