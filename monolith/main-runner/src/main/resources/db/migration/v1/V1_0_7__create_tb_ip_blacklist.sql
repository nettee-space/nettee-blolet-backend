CREATE SCHEMA IF NOT EXISTS "auth";

CREATE TABLE IF NOT EXISTS "auth"."ip_blacklist" (
    "id"         BIGINT      NOT NULL,
    "ip_address" INET,
    "reason"     VARCHAR(255),
    "blocked_at" TIMESTAMP
);

COMMENT ON COLUMN "auth"."ip_blacklist"."id"         IS 'IP 블랙리스트 ID';
COMMENT ON COLUMN "auth"."ip_blacklist"."ip_address" IS '차단 IP 주소';
COMMENT ON COLUMN "auth"."ip_blacklist"."reason"     IS '차단 사유';
COMMENT ON COLUMN "auth"."ip_blacklist"."blocked_at" IS '차단 시작 시각';

ALTER TABLE "auth"."ip_blacklist" ADD CONSTRAINT "pk_ip_blacklist" PRIMARY KEY ("id");
