-- 네임스페이스 사용 필수 (ex: dummy.~)
SET dummy.USER_ID               = '33333333310000000';
SET dummy.PROFILE_ID            = '33333333320000000';
SET dummy.BLOG_ID               = '33333333330000000';
SET dummy.USERNAME              = 'sun';
SET dummy.USER_RAW_PASSWORD     = 'Blolet1225!';
SET dummy.USER_ENCODED_PASSWORD =
    '$argon2id$v=19$m=32768,t=4,p=1$y2CjGj9VT7FUmmRu1brflw$h9MfPg2iT2Doh9w/J1ORBpmOE0/ZWVa2q4kMIPlmqc8';
-- current_setting('dummy.USERID')::bigint

INSERT INTO "auth"."user" (
    "id",
    "login_id",
    "username",
    "encoded_password",
    "nickname",
    "email"
) VALUES (
    current_setting('dummy.USER_ID')::bigint,
    'sun123',
    current_setting('dummy.USERNAME')::varchar,
    -- raw password: Blolet1225!
    current_setting('dummy.USER_ENCODED_PASSWORD')::varchar,
    '동굴',
    'sun@gmail.com'
);
