ALTER TABLE article.draft_block
ALTER COLUMN status TYPE INTEGER USING status::integer;
