-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS analytics (
     id                    BIGINT      NOT NULL,  -- 애널리틱스 PK
     analyzed_at           DATE        NULL,      -- 분석 기준 날짜
     average_duration_time INTEGER     NULL,      -- 포스트의 평균 체류 시간
     bounce_rate           INTEGER     NULL,      -- 포스트의 이탈률
     unique_visitors       INTEGER     NULL,      -- 해당 날짜에 특정 게시글을 방문한 고유 방문자 수
     views                 INTEGER     NULL,      -- 특정 게시글이 해당 날짜에 조회된 횟수
     comments              INTEGER     NULL,      -- 댓글 수
     likes                 INTEGER     NULL,      -- 좋아요 수
     shares                INTEGER     NULL,      -- 공유 수
     created_at            TIMESTAMP   NULL,      -- 애널리틱스 생성일자
     updated_at            TIMESTAMP   NULL,      -- 애널리틱스 수정일자
     article_id            BIGINT      NULL,      -- 아티클(게시글) PK

     CONSTRAINT pk_analytics PRIMARY KEY (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE analytics IS '애널리틱스 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN analytics.id                    IS '애널리틱스 ID';
COMMENT ON COLUMN analytics.analyzed_at           IS '분석 기준 날짜';
COMMENT ON COLUMN analytics.average_duration_time IS '포스트의 평균 체류 시간';
COMMENT ON COLUMN analytics.bounce_rate           IS '포스트의 이탈률';
COMMENT ON COLUMN analytics.unique_visitors       IS '해당 날짜에 고유 방문자 수';
COMMENT ON COLUMN analytics.views                 IS '조회된 횟수';
COMMENT ON COLUMN analytics.comments              IS '댓글 수';
COMMENT ON COLUMN analytics.likes                 IS '좋아요 수';
COMMENT ON COLUMN analytics.shares                IS '공유 수';
COMMENT ON COLUMN analytics.created_at            IS '애널리틱스 생성일자';
COMMENT ON COLUMN analytics.updated_at            IS '애널리틱스 수정일자';
COMMENT ON COLUMN analytics.article_id            IS '아티클 ID';
