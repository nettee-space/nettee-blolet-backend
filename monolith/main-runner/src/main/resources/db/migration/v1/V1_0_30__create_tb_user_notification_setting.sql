-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS user_notification_setting (
     id             BIGINT    NOT NULL,  -- 알림 설정 정보에 대한 PK
     user_id        BIGINT    NOT NULL,  -- 수신자를 식별하기 위한 ID
     on_comment     BOOLEAN   NULL,      -- 댓글에 대한 알림 설정
     on_reply       BOOLEAN   NULL,      -- 답글에 대한 알림 설정
     on_subscribe   BOOLEAN   NULL,      -- 구독에 대한 알림 설정 (다른 사람이 자신을 구독하는 것)
     on_newsfeed    BOOLEAN   NULL,      -- 뉴스레터에 대한 알림 설정 (내가 구독하고 있는 사람이 글을 작성한 경우)

     CONSTRAINT pk_user_notification_setting PRIMARY KEY (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE user_notification_setting IS '알림 설정 정보 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN user_notification_setting.id            IS '유저 알림 설정 정보 ID';
COMMENT ON COLUMN user_notification_setting.user_id       IS '사용자 ID';
COMMENT ON COLUMN user_notification_setting.on_comment    IS '댓글 알림 설정';
COMMENT ON COLUMN user_notification_setting.on_reply      IS '답글 알림 설정';
COMMENT ON COLUMN user_notification_setting.on_subscribe  IS '구독 알림 설정';
COMMENT ON COLUMN user_notification_setting.on_newsfeed   IS '뉴스레터 알림 설정';
