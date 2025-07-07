-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS notification (
    id          BIGINT      NOT NULL,  -- 알림 데이터에 대한 PK
    user_id     BIGINT      NOT NULL,  -- 수신자를 식별하기 위한 ID
    type        INTEGER     NULL,      -- 알림에 대한 종류(회원가입, 댓글, 답글, 구독, 뉴스레터)
    content     VARCHAR     NULL,      -- 알림에 대한 구체적인 내용
    is_read     BOOLEAN     NULL,      -- 알림에 대해 사용자가 읽었는지 (True or False)
    created_at  TIMESTAMP   NULL,      -- 알림 생성 시각
    read_at     TIMESTAMP   NULL,      -- 알림을 읽은 시각

    CONSTRAINT pk_notification PRIMARY KEY (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE notification IS '알림 내역 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN notification.id         IS '알림 ID';
COMMENT ON COLUMN notification.user_id    IS '사용자 ID';
COMMENT ON COLUMN notification.type       IS '알림 유형';
COMMENT ON COLUMN notification.content    IS '구체적인 내용';
COMMENT ON COLUMN notification.is_read    IS '읽음 여부';
COMMENT ON COLUMN notification.created_at IS '알림 생성 시각';
COMMENT ON COLUMN notification.read_at    IS '알림 읽음 시각';
