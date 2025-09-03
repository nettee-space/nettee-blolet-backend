CREATE SCHEMA IF NOT EXISTS "auth";

CREATE TABLE IF NOT EXISTS "auth"."user" (
    "id"	            BIGINT         ,
    "email"	            VARCHAR(255)   ,
    -- 인증
    "username"	        VARCHAR(255)   ,
    "encoded_password"	VARCHAR(255)   ,
    -- 보호
    "login_retry_count"	INT	            DEFAULT 0,
    "locked_until"	    TIMESTAMP      ,
    -- 프로필 비정규화
    "nickname"	        VARCHAR(255)   ,
    -- 공통 컬럼
    "status"	        INT	            DEFAULT 20          NOT NULL,
    "created_at"	    TIMESTAMP		DEFAULT NOW()       NOT NULL,
    "updated_at"	    TIMESTAMP		DEFAULT NOW()       NOT NULL
);

COMMENT ON COLUMN "auth"."user"."id"                  IS '사용자 테이블 PK';
COMMENT ON COLUMN "auth"."user"."email"               IS '사용자 이메일 주소(로그인 ID)';
COMMENT ON COLUMN "auth"."user"."username"            IS '사용자의실명';
COMMENT ON COLUMN "auth"."user"."encoded_password"    IS '로그인 비밀번호';
COMMENT ON COLUMN "auth"."user"."login_retry_count"   IS '로그인 실패 횟수';
COMMENT ON COLUMN "auth"."user"."locked_until"        IS '계정 잠금 해제 예정 시각, 잠금 시 사용';
COMMENT ON COLUMN "auth"."user"."nickname"            IS '사용자닉네임';
COMMENT ON COLUMN "auth"."user"."status"              IS
        '사용자 계정 상태 코드: PENDING(10, 가입 대기), ACTIVE(20, 활동), SUSPENDED(30, 정지), PROTECTED(40, 보호), REMOVED(0, 탈퇴)';
COMMENT ON COLUMN "auth"."user"."created_at"          IS '생성일자';
COMMENT ON COLUMN "auth"."user"."updated_at"          IS '수정일자';

ALTER TABLE "auth"."user" ADD CONSTRAINT "pk_user" PRIMARY KEY ("id");
ALTER TABLE "auth"."user" ADD CONSTRAINT "uq_user_email" UNIQUE ("email");
ALTER TABLE "auth"."user" ADD CONSTRAINT "uq_user_username" UNIQUE ("username");
ALTER TABLE "auth"."user" ADD CONSTRAINT "uq_user_nickname" UNIQUE ("nickname");
