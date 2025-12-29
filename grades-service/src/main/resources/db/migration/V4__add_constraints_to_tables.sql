CREATE UNIQUE INDEX uk_subjects_base_not_deleted
ON subjects (name, grade, type)
WHERE deleted_at IS NULL
  AND course IS NULL;


CREATE UNIQUE INDEX uk_subjects_course_specific_not_deleted
ON subjects (name, grade, course, type)
WHERE deleted_at IS NULL
  AND course IS NOT NULL;



CREATE UNIQUE INDEX uk_grades_enrollment_subject_bimester_not_deleted
ON grades (enrollment_id, subject_id, bimester)
WHERE deleted_at IS NULL;
