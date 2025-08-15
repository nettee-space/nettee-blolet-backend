-- Namespaced session variables
--   네임스페이스 사용 필수 (ex: dummy.~)
-- Use:
--  current_setting('dummy.USERID')::bigint

-- USER 1
SET dummy.USER_ID               = '33333333310000003';
SET dummy.PROFILE_ID            = '33333333310000004';
SET dummy.BLOG_ID               = '33333333310000005';
SET dummy.USERNAME              = 'sun';

-- USER 2
SET dummy.USER_ID2              = '33333333320000006';
SET dummy.PROFILE_ID2           = '33333333320000007';
SET dummy.BLOG_ID2              = '33333333320000008';
SET dummy.USERNAME2             = 'moon';

-- 비밀번호
SET dummy.USER_RAW_PASSWORD     = 'Blolet1225!';
SET dummy.USER_ENCODED_PASSWORD =
    '$argon2id$v=19$m=32768,t=4,p=1$y2CjGj9VT7FUmmRu1brflw$h9MfPg2iT2Doh9w/J1ORBpmOE0/ZWVa2q4kMIPlmqc8';

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
    current_setting('dummy.USER_ENCODED_PASSWORD')::varchar,
    '동굴',
    'sun@gmail.com'
), (
    current_setting('dummy.USER_ID2')::bigint,
    'moon123',
    current_setting('dummy.USERNAME2')::varchar,
    -- raw password: Blolet1225!
    current_setting('dummy.USER_ENCODED_PASSWORD')::varchar,
    '침착너구리',
    'moon@gmail.com'
);

INSERT INTO "profile"."profile" (
    "id",
    "user_id",
    "nickname",
    "gender",
    "birth",
    "profile_url",
    "bio",
    "tel",
    "occupation",
    "interest"
) VALUES (
    current_setting('dummy.PROFILE_ID')::bigint,
    current_setting('dummy.USER_ID')::bigint,
    '동굴',
    'NOT_THAT_SIMPLE',
    '2025-08-15'::date,
    'https://sdmntprnorthcentralus.oaiusercontent.com/files/00000000-2228-622f-a21e-41f365335041/raw',
    '매너 있고 다정한 ^^;;; 젊은 남자 개발자입니다. 여친 있음 ^^;',
    '010-1234-1234',
    '백엔드 엔지니어',
    '서울 데이트 코스,공부하기 좋은 카페,느좋카'
), (
    current_setting('dummy.PROFILE_ID2')::bigint,
    current_setting('dummy.USER_ID2')::bigint,
    '침착너구리',
    '안드로진뭐시기',
    '2025-08-15'::date,
    'https://sdmntprnorthcentralus.oaiusercontent.com/files/00000000-2228-622f-a21e-41f365335041/raw',
    '젊은 남자 개발자입니다. 남친 있음 ^^;',
    '010-1234-1235',
    '프론트엔드 엔지니어',
    '전국 데이트 코스,공부하기 좋은 카페,느좋카'
);

INSERT INTO "blog"."blog" (
    "id",
    "user_id",
    "profile_id",
    "username",
    "nickname",
    "name",
    "url_identifier"
) VALUES (
    current_setting('dummy.BLOG_ID')::bigint,
    current_setting('dummy.USER_ID')::bigint,
    current_setting('dummy.PROFILE_ID')::bigint,
    current_setting('dummy.USERNAME')::varchar,
    '동굴',
    '동굴',
    current_setting('dummy.USERNAME')::varchar
), (
    current_setting('dummy.BLOG_ID2')::bigint,
    current_setting('dummy.USER_ID2')::bigint,
    current_setting('dummy.PROFILE_ID2')::bigint,
    current_setting('dummy.USERNAME2')::varchar,
    '침착너구리',
    '침착 너구리의 블로그',
    current_setting('dummy.USERNAME2')::varchar
);
