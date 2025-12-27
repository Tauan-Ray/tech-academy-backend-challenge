CREATE TABLE enrollments (
    id_enrollment SERIAL PRIMARY KEY,

    student_id INT NOT NULL,
    classroom_id INT NOT NULL,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_enrollments_student
     FOREIGN KEY (student_id)
         REFERENCES students (id_student)
         ON DELETE RESTRICT,

    CONSTRAINT fk_enrollments_classroom
     FOREIGN KEY (classroom_id)
         REFERENCES classrooms (id_classroom)
         ON DELETE RESTRICT
);
