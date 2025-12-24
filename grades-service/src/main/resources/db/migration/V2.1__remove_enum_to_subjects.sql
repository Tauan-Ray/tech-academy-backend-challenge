ALTER TABLE subjects
    ALTER COLUMN type TYPE VARCHAR(30)
        USING type::text;

DROP TYPE subject_type;
