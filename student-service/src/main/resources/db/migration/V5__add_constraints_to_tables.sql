ALTER TABLE students DROP CONSTRAINT students_email_key;

CREATE UNIQUE INDEX uk_students_email_not_deleted
ON students (email)
WHERE deleted_at IS NULL;


CREATE UNIQUE INDEX uk_classrooms_year_course_grade_not_deleted
ON classrooms (year, course, grade)
WHERE deleted_at IS NULL;


CREATE UNIQUE INDEX uk_enrollments_student_classroom_not_deleted
ON enrollments (student_id, classroom_id)
WHERE deleted_at IS NULL;