CREATE TABLE grades (
    id_grade SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    CONSTRAINT fk_grades_subject
        FOREIGN KEY (subject_id)
        REFERENCES subjects (id_subject),
    score NUMERIC(4,2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP
);
