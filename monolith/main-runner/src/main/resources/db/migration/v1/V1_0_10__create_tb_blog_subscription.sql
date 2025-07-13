CREATE TABLE "blog"."blog_subscription" (
    "id"	        BIGINT		    ,
    "user_id"	    BIGINT		    ,
    "blog_id"	    BIGINT		    ,
    "email_allowed"	BOOLEAN	        DEFAULT false,
    "noti_allowed"	BOOLEAN	        DEFAULT false,
    "created_at"	TIMESTAMP		NOT NULL,
    "updated_at"	TIMESTAMP		NOT NULL
);

COMMENT ON COLUMN "blog"."blog_subscription"."id" IS '구독 테이블 PK';
COMMENT ON COLUMN "blog"."blog_subscription"."user_id" IS '사용자 테이블 PK';
COMMENT ON COLUMN "blog"."blog_subscription"."blog_id" IS '블로그 테이블의 PK';
COMMENT ON COLUMN "blog"."blog_subscription"."email_allowed" IS '이메일 허용 여부';
COMMENT ON COLUMN "blog"."blog_subscription"."noti_allowed" IS '알림 허용 여부';
COMMENT ON COLUMN "blog"."blog_subscription"."created_at" IS '생성일자';
COMMENT ON COLUMN "blog"."blog_subscription"."updated_at" IS '수정일자';

ALTER TABLE "blog"."blog_subscription" ADD CONSTRAINT "pk_blog_subscription" PRIMARY KEY ("id");
ALTER TABLE "blog"."blog_subscription" ADD CONSTRAINT "uq_blog_subscription_user_id_blog_id" UNIQUE ("user_id", "blog_id");
