-- 1. 테이블 생성
CREATE TABLE IF NOT EXISTS notification_message (
    id          BIGINT      NOT NULL,  -- 알림 메시지 임의의 PK
    message_id  VARCHAR     NULL,      -- 메시지의 고유 ID (문자열일 수 있음)
    content     VARCHAR     NULL,      -- 메시지 내용
    status      INTEGER     NULL,      -- 메시지 처리에 대한 상태 (대기, 성공, 실패)
    created_at  TIMESTAMP   NULL,      -- 메시지 생성 시각

    CONSTRAINT pk_notification_message PRIMARY KEY (id)
);

-- 2. 테이블 설명
COMMENT ON TABLE notification_message IS '알림에 대한 메시지 처리 내역 테이블';

-- 3. 컬럼 설명
COMMENT ON COLUMN notification_message.id         IS '알림 메시지 임의의 PK';
COMMENT ON COLUMN notification_message.message_id IS '메시지의 고유 ID';
COMMENT ON COLUMN notification_message.content    IS '메시지 내용';
COMMENT ON COLUMN notification_message.status     IS '처리 상태';
COMMENT ON COLUMN notification_message.created_at IS '메시지 생성 시각';
