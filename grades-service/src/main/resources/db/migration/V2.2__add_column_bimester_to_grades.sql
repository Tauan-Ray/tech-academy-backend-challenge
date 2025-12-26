ALTER TABLE grades
ADD COLUMN bimester VARCHAR(15);

UPDATE grades
SET bimester = 'BIMESTER_1'
WHERE bimester IS NULL;

ALTER TABLE grades
ALTER COLUMN bimester SET NOT NULL;

ALTER TABLE grades
ADD CONSTRAINT chk_grades_bimester
CHECK (bimester IN (
    'BIMESTER_1',
    'BIMESTER_2',
    'BIMESTER_3',
    'BIMESTER_4'
));
