-- 1. 테이블 생성: 답글 신고 누적
CREATE TABLE IF NOT EXISTS reply_report_count (
    id        BIGINT    NOT NULL,  -- 답글 신고 누적 횟수 PK
    reply_id  BIGINT    NOT NULL,  -- 답글을 식별하기 위한 ID
    count     INTEGER   NULL,      -- 누적 신고 횟수 (사용자+답글 조합별 1회 카운트)

    CONSTRAINT pk_reply_report_count PRIMARY KEY (id)
);

-- 2. 테이블 설명: 답글 신고 누적
COMMENT ON TABLE reply_report_count IS '답글 신고 누적 횟수 테이블';

-- 3. 컬럼 설명: 답글 신고 누적
COMMENT ON COLUMN reply_report_count.id       IS '답글 신고 누적 횟수 ID';
COMMENT ON COLUMN reply_report_count.reply_id IS '답글 ID';
COMMENT ON COLUMN reply_report_count.count    IS '누적 신고 횟수';