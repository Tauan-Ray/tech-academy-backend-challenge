CREATE TABLE classrooms (
    id_classroom SERIAL PRIMARY KEY,
    year INT NOT NULL,
    course VARCHAR(100) NOT NULL,
    grade INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP
);

ALTER TABLE students
ADD COLUMN classroom_id INT NOT NULL;

ALTER TABLE students
ADD CONSTRAINT fk_classroom
FOREIGN KEY (classroom_id)
REFERENCES classrooms(id_classroom)
ON DELETE RESTRICT;