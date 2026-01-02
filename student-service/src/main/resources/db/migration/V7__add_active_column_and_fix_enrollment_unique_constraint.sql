ALTER TABLE enrollments
ADD COLUMN active BOOLEAN NOT NULL DEFAULT true;

CREATE UNIQUE INDEX uk_enrollments_student_active
ON enrollments (student_id)
WHERE deleted_at IS NULL AND active = true;

