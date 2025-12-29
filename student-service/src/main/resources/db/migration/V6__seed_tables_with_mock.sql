-- =========================
-- CLASSROOMS
-- =========================
INSERT INTO classrooms (
    year,
    course,
    grade,
    created_at,
    updated_at,
    deleted_at
) VALUES
-- Informática
(2022, 'Informática', 1, NOW(), NOW(), NULL),
(2023, 'Informática', 2, NOW(), NOW(), NULL),
(2024, 'Informática', 3, NOW(), NOW(), NULL),

-- Enfermagem
(2022, 'Enfermagem', 1, NOW(), NOW(), NULL),
(2023, 'Enfermagem', 2, NOW(), NOW(), NULL),
(2024, 'Enfermagem', 3, NOW(), NOW(), NULL),

-- Hospedagem
(2023, 'Hospedagem', 1, NOW(), NOW(), NULL),
(2024, 'Hospedagem', 2, NOW(), NOW(), NULL),

-- Modelagem
(2022, 'Modelagem', 1, NOW(), NOW(), NULL),
(2023, 'Modelagem', 2, NOW(), NOW(), NULL),
(2024, 'Modelagem', 3, NOW(), NOW(), NULL);


-- =========================
-- STUDENTS
-- =========================
INSERT INTO students (
    name,
    email,
    created_at,
    updated_at,
    deleted_at
) VALUES
('Ana Beatriz Souza', 'ana.souza@techacademy.com', NOW(), NOW(), NULL),
('Lucas Henrique Lima', 'lucas.lima@techacademy.com', NOW(), NOW(), NULL),
('Mariana Costa', 'mariana.costa@techacademy.com', NOW(), NOW(), NULL),
('Pedro Alves', 'pedro.alves@techacademy.com', NOW(), NOW(), NULL),
('Camila Rocha', 'camila.rocha@techacademy.com', NOW(), NOW(), NULL),
('Rafael Martins', 'rafael.martins@techacademy.com', NOW(), NOW(), NULL),
('Juliana Ferreira', 'juliana.ferreira@techacademy.com', NOW(), NOW(), NULL),
('Bruno Ribeiro', 'bruno.ribeiro@techacademy.com', NOW(), NOW(), NULL),
('Larissa Pacheco', 'larissa.pacheco@techacademy.com', NOW(), NOW(), NULL),
('Diego Nogueira', 'diego.nogueira@techacademy.com', NOW(), NOW(), NULL);


-- =========================
-- ENROLLMENTS
-- =========================

-- 1º ano
INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2022 AND c.course = 'Informática' AND c.grade = 1
WHERE s.email = 'ana.souza@techacademy.com';


INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2022 AND c.course = 'Enfermagem' AND c.grade = 1
WHERE s.email = 'lucas.lima@techacademy.com';


INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2023 AND c.course = 'Hospedagem' AND c.grade = 1
WHERE s.email = 'mariana.costa@techacademy.com';


INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2022 AND c.course = 'Modelagem' AND c.grade = 1
WHERE s.email = 'pedro.alves@techacademy.com';


-- 2º ano
INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2023 AND c.course = 'Informática' AND c.grade = 2
WHERE s.email = 'camila.rocha@techacademy.com';


INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2023 AND c.course = 'Enfermagem' AND c.grade = 2
WHERE s.email = 'rafael.martins@techacademy.com';


INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2023 AND c.course = 'Modelagem' AND c.grade = 2
WHERE s.email = 'juliana.ferreira@techacademy.com';


-- 3º ano
INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2024 AND c.course = 'Informática' AND c.grade = 3
WHERE s.email = 'bruno.ribeiro@techacademy.com';


INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2024 AND c.course = 'Enfermagem' AND c.grade = 3
WHERE s.email = 'larissa.pacheco@techacademy.com';


INSERT INTO enrollments (
    student_id,
    classroom_id,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    s.id_student,
    c.id_classroom,
    NOW(),
    NOW(),
    NULL
FROM students s
JOIN classrooms c ON c.year = 2024 AND c.course = 'Modelagem' AND c.grade = 3
WHERE s.email = 'diego.nogueira@techacademy.com';
