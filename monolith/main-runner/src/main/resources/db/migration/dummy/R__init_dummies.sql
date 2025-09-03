-- Namespaced session variables
--  네임스페이스 사용 필수 (ex: dummy.~)
--  Use:
--    current_setting('dummy.USERID')::bigint

-- ╭──────────────────────╮
--     회원 및 블로그 초기화
--    Auth, Profile Blog
-- ╰──────────────────────╯

-- USER 1
SET LOCAL dummy.USER_ID               = '33333333310000003';
SET LOCAL dummy.PROFILE_ID            = '33333333310000004';
SET LOCAL dummy.BLOG_ID               = '33333333310000005';
SET LOCAL dummy.USERNAME              = 'sun';

-- USER 2
SET LOCAL dummy.USER_ID2              = '33333333320000006';
SET LOCAL dummy.PROFILE_ID2           = '33333333320000007';
SET LOCAL dummy.BLOG_ID2              = '33333333320000008';
SET LOCAL dummy.USERNAME2             = 'moon';

-- 비밀번호
SET LOCAL dummy.USER_RAW_PASSWORD     = 'Blolet1225!';
SET LOCAL dummy.USER_ENCODED_PASSWORD =
    '$argon2id$v=19$m=32768,t=4,p=1$y2CjGj9VT7FUmmRu1brflw$h9MfPg2iT2Doh9w/J1ORBpmOE0/ZWVa2q4kMIPlmqc8';

INSERT INTO "auth"."user" (
    "id",
    "username",
    "encoded_password",
    "nickname",
    "email"
) VALUES (
    current_setting('dummy.USER_ID')::bigint,
    current_setting('dummy.USERNAME')::varchar,
    current_setting('dummy.USER_ENCODED_PASSWORD')::varchar,
    '동굴',
    'sun@gmail.com'
), (
    current_setting('dummy.USER_ID2')::bigint,
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

-- ╭──────────────────────────────╮
--    Draft, Article, 시리즈 초기화
-- ╰──────────────────────────────╯

SET LOCAL dummy.DRAFT_ID_BASE           = '3333333333';
SET LOCAL dummy.DRAFT_ID_DYNAMIC_SIZE   = 7;

SET LOCAL dummy.ARTICLE_ID_BASE         = '3333333334';
SET LOCAL dummy.ARTICLE_ID_DYNAMIC_SIZE = 7;

-- 상태 상수 계산 → 세션 변수(dummy.~)에 LOCAL로 저장
WITH v(name, val) AS (
    VALUES
    -- draft status
    ('dummy.DRAFT_REMOVED',     (X'00000000'::bit(32)::int)::text),
    ('dummy.DRAFT_PENDING',     (X'6C000100'::bit(32)::int)::text),
    ('dummy.DRAFT_PUBLISHED',   (X'6C000200'::bit(32)::int)::text),
    ('dummy.DRAFT_UPDATED',     (X'6C000210'::bit(32)::int)::text),
    ('dummy.DRAFT_SUSPENDED',   (X'28000400'::bit(32)::int)::text),

    -- draft block status
    ('dummy.DRAFT_BLOCK_REMOVED',   (X'00000000'::bit(32)::int)::text),
    ('dummy.DRAFT_BLOCK_PENDING',   (X'6C000100'::bit(32)::int)::text),
    ('dummy.DRAFT_BLOCK_PUBLISHED', (X'6C000200'::bit(32)::int)::text),
    ('dummy.DRAFT_BLOCK_UPDATED',   (X'6C000210'::bit(32)::int)::text),
    ('dummy.DRAFT_BLOCK_SUSPENDED', (X'28000400'::bit(32)::int)::text),

    -- article status
    ('dummy.ARTICLE_REMOVED',   (X'08000000'::bit(32)::int)::text),
    ('dummy.ARTICLE_PENDING',   (X'6C000100'::bit(32)::int)::text),
    ('dummy.ARTICLE_ACTIVE',    (X'6C000200'::bit(32)::int)::text),
    ('dummy.ARTICLE_SUSPENDED', (X'28000400'::bit(32)::int)::text)
)
SELECT set_config(name, val, true)  -- true = LOCAL
FROM v;

-- ─────────────────────────────────────────────────────────────────────────────
-- Draft(115) 생성: 1..58=PUBLISHED, 59..110=PENDING, 111..115=REMOVED
--  - id/경로는 연속 번호 사용
-- ─────────────────────────────────────────────────────────────────────────────

INSERT INTO "article"."draft" (
    id,
    blog_id,
    title,
    "path",
    status
)
    SELECT
        (current_setting('dummy.DRAFT_ID_BASE')::text
            || lpad(gs::text, current_setting('dummy.DRAFT_ID_DYNAMIC_SIZE')::int, '0'))::bigint AS id,
            current_setting('dummy.BLOG_ID')::bigint AS blog_id,
            'title_' || gs AS title,
        'post-' || gs AS "path",
        CASE
            WHEN gs BETWEEN 1 AND 53  THEN current_setting('dummy.DRAFT_PUBLISHED')::int    -- PUBLISHED
            WHEN gs BETWEEN 54 AND 58  THEN current_setting('dummy.DRAFT_SUSPENDED')::int   -- SUSPENDED
            WHEN gs BETWEEN 59 AND 110 THEN current_setting('dummy.DRAFT_PENDING')::int     -- PENDING
            ELSE current_setting('dummy.DRAFT_REMOVED')::int                                -- REMOVED
        END AS status
    FROM generate_series(1, 115) gs;

-- ─────────────────────────────────────────────────────────────────────────────
-- Article(63) 생성 및 Draft.article_id 연결
--  - PUBLISHED Draft 중 id 오름차순 53개 → Article ACTIVE
--  - 나머지 PUBLISHED 5개 → Article SUSPENDED
--  - REMOVED Draft 5개 → Article REMOVED
--  - Article ID는 dummy.ARTICLE_ID_BASE + 연속번호(패딩)
--  - Article.path/title 은 매핑된 Draft와 동일
-- ─────────────────────────────────────────────────────────────────────────────

WITH
    pub AS (
        -- PUBLISHED
        SELECT
            d.*, ROW_NUMBER() OVER (ORDER BY d.id) AS rn
        FROM "article"."draft" d
        WHERE
            d.blog_id = current_setting('dummy.BLOG_ID')::bigint
            AND d.status = current_setting('dummy.DRAFT_PUBLISHED')::int
    ),
    rm AS (
        -- REMOVED
        SELECT
            d.*
        FROM "article"."draft" d
        WHERE
            d.blog_id = current_setting('dummy.BLOG_ID')::bigint
            AND d.status = current_setting('dummy.DRAFT_REMOVED')::int
        ORDER BY d.id
    ),
    to_insert AS (
        -- ACTIVE
        SELECT
            p.blog_id,
            p.id AS draft_id,
            p.title || '_article' AS title,
            p."path",
            current_setting('dummy.ARTICLE_ACTIVE')::int AS article_status,
            p.rn AS ord
        FROM pub p WHERE p.rn <= 53
        UNION ALL
            -- SUSPENDED
            SELECT
                p.blog_id,
                p.id AS draft_id,
                p.title || '_article' AS title,
                p."path",
                current_setting('dummy.ARTICLE_SUSPENDED')::int AS article_status,
                p.rn
            FROM pub p WHERE p.rn > 53
        UNION ALL
            -- REMOVED
            SELECT
                r.blog_id,
                r.id AS draft_id,
                r.title || '_article' AS title,
                r."path",
                current_setting('dummy.ARTICLE_REMOVED')::int AS article_status,
                r.id::bigint
            FROM rm r
    ),
    ins AS (
        INSERT INTO "article"."article" (
            id,
            blog_id,
            draft_id,
            title,
            "path",
            status,
            total_views,
            total_likes,
            total_shares
        )
        SELECT
            (
                current_setting('dummy.ARTICLE_ID_BASE')::text
                || LPAD(
                        ROW_NUMBER() OVER (ORDER BY ord)::text,
                        current_setting('dummy.ARTICLE_ID_DYNAMIC_SIZE')::int,
                        '0'
                )
            )::bigint AS id,
            blog_id,
            draft_id,
            title,
            "path",
            article_status,
            0,
            0,
            0
        FROM to_insert
        RETURNING
            id,
            blog_id,
            "path"
    )

UPDATE "article"."draft" d
SET
    article_id = i.id
FROM ins i
WHERE d.blog_id = i.blog_id
    AND d."path" = i."path";

-- ─────────────────────────────────────────────────────────────────────────────
-- Series(53) 생성: title 'Series 1'..'Series 53'
-- ─────────────────────────────────────────────────────────────────────────────
INSERT INTO "article"."series" (
    blog_id,
    title,
    "description",
    display_order
)
    SELECT
        current_setting('dummy.BLOG_ID')::bigint,
        'Series ' || gs,
        'Demo series #' || gs,
        gs
    FROM generate_series(1, 53) gs;

-- ─────────────────────────────────────────────────────────────────────────────
-- SeriesArticle: 첫 번째 시리즈에만 묶기
--  - PENDING Draft 52개 (draft_id 세팅)
--  - ACTIVE 53개 + SUSPENDED 5개 (article_id 세팅)
-- ─────────────────────────────────────────────────────────────────────────────
WITH
    first_series AS (
        SELECT
            MIN(s.id) AS sid
        FROM "article"."series" s
        WHERE s.blog_id = current_setting('dummy.BLOG_ID')::bigint
    ),
    pending_drafts AS (
        SELECT
            d.id,
            ROW_NUMBER() OVER (ORDER BY d.id) AS rn
        FROM "article"."draft" d
        WHERE
            d.blog_id = current_setting('dummy.BLOG_ID')::bigint
            -- PENDING
            AND d.status = current_setting('dummy.DRAFT_PENDING')::int
    ),
    active_susp_articles AS (
        SELECT
            a.id,
            a.draft_id,
            ROW_NUMBER() OVER (ORDER BY a.id) AS rn
        FROM "article"."article" a
        WHERE
            a.blog_id = current_setting('dummy.BLOG_ID')::bigint
            -- ACTIVE, SUSPENDED
            AND a.status IN (
                current_setting('dummy.ARTICLE_ACTIVE')::int,
                current_setting('dummy.ARTICLE_SUSPENDED')::int
            )
    ),
    active_susp_count AS (
        SELECT COUNT(*) AS cnt FROM active_susp_articles
    ),
    ins_pending AS (
        -- PENDING drafts 52개
        INSERT INTO "article"."series_article"(
            series_id,
            draft_id,
            display_order
        )
        SELECT
            f.sid,
            p.id,
            p.rn + c.cnt
        FROM first_series f
            CROSS JOIN active_susp_count c
            JOIN (SELECT * FROM pending_drafts ORDER BY rn LIMIT 52) p
                ON TRUE
            RETURNING 1
    )
-- ACTIVE 53개 + SUSPENDED 5개 = 58개
INSERT INTO "article"."series_article" (
    series_id,
    article_id,
    draft_id,
    display_order
)
SELECT
    f.sid,
    a.id,
    a.draft_id,
    a.rn
FROM first_series f
    JOIN active_susp_articles a
        ON TRUE;

-- statistics
--  "auth"."user":          2 rows
--  "profile"."profile":    2 rows
--  "blog"."blog":          2 rows