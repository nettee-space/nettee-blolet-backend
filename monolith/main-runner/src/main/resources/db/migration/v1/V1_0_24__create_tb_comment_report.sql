-- 1. 테이블 생성: 댓글 신고
CREATE TABLE IF NOT EXISTS comment_report (
    id          BIGINT    NOT NULL,  -- 댓글 신고내역 PK
    title       TEXT      NULL,      -- 제목
    content     TEXT      NULL,      -- 내용
    comment_id  BIGINT    NULL,      -- 댓글을 식별하기 위한 ID
    user_id     BIGINT    NULL,      -- 사용자 테이블 PK

    CONSTRAINT pk_comment_report PRIMARY KEY (id)
);

-- 2. 테이블 설명: 댓글 신고
COMMENT ON TABLE comment_report IS '댓글 신고 테이블';

-- 3. 컬럼 설명: 댓글 신고
COMMENT ON COLUMN comment_report.id         IS '댓글 신고내역 ID';
COMMENT ON COLUMN comment_report.title      IS '제목';
COMMENT ON COLUMN comment_report.content    IS '내용';
COMMENT ON COLUMN comment_report.comment_id IS '댓글  ID';
COMMENT ON COLUMN comment_report.user_id    IS '사용자 ID';