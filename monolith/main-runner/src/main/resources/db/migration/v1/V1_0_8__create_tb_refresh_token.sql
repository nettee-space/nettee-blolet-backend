CREATE TABLE "auth"."refresh_token" (
    "id"	        BIGINT		    ,
    "user_id"	    BIGINT		    ,
    "username"      VARCHAR(255)    ,
    "token"	        VARCHAR(255)    ,
    "device_info"	VARCHAR		    ,
    "created_at"	TIMESTAMP		NOT NULL,
    "expired_at"	TIMESTAMP		NOT NULL
);

COMMENT ON COLUMN "auth"."refresh_token"."id"          IS '사용자 토큰 ID';
COMMENT ON COLUMN "auth"."refresh_token"."user_id"     IS '사용자 테이블 PK';
COMMENT ON COLUMN "auth"."refresh_token"."user_id"     IS '사용자 계정 이름';
COMMENT ON COLUMN "auth"."refresh_token"."token"       IS '리프레시 토큰';
COMMENT ON COLUMN "auth"."refresh_token"."device_info" IS '로그인 기기 정보';
COMMENT ON COLUMN "auth"."refresh_token"."created_at"  IS '토큰 생성 시각';
COMMENT ON COLUMN "auth"."refresh_token"."expired_at"  IS '토큰 만료 시각';

ALTER TABLE "auth"."refresh_token" ADD CONSTRAINT "pk_refresh_token" PRIMARY KEY ("id");