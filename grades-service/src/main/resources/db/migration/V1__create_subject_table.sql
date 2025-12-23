CREATE TYPE subject_type AS ENUM ('BASE', 'COURSE_SPECIFIC');

CREATE TABLE subjects (
    id_subject SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    grade INT NOT NULL,
    type subject_type NOT NULL,
    course VARCHAR(50),
    workload INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP
);
