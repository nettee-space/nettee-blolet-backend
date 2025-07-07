-- 1. 테이블 생성: 댓글 신고 누적
CREATE TABLE IF NOT EXISTS comment_report_count (
    id          BIGINT    NOT NULL,  -- 댓글 신고 누적 횟수 PK
    comment_id  BIGINT    NOT NULL,  -- 댓글을 식별하기 위한 ID
    count       INTEGER   NULL,      -- 누적 신고 횟수 (사용자+댓글 조합별 1회 카운트)

    CONSTRAINT pk_comment_report_count PRIMARY KEY (id)
);

-- 2. 테이블 설명: 댓글 신고 누적
COMMENT ON TABLE comment_report_count IS '댓글 신고 누적 횟수 테이블';

-- 3. 컬럼 설명: 댓글 신고 누적
COMMENT ON COLUMN comment_report_count.id         IS '댓글 신고 누적 횟수 ID';
COMMENT ON COLUMN comment_report_count.comment_id IS '댓글 ID';
COMMENT ON COLUMN comment_report_count.count      IS '누적 신고 횟수';