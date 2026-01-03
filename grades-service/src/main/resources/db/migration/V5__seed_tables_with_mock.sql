INSERT INTO subjects (
    name,
    grade,
    type,
    course,
    workload,
    created_at,
    updated_at,
    deleted_at
) VALUES
-- Grade 1
('Português', 1, 'BASE', NULL, 200, NOW(), NOW(), NULL),
('Matemática', 1, 'BASE', NULL, 200, NOW(), NOW(), NULL),
('História', 1, 'BASE', NULL, 120, NOW(), NOW(), NULL),
('Geografia', 1, 'BASE', NULL, 120, NOW(), NOW(), NULL),

-- Grade 2
('Português', 2, 'BASE', NULL, 200, NOW(), NOW(), NULL),
('Matemática', 2, 'BASE', NULL, 200, NOW(), NOW(), NULL),
('Física', 2, 'BASE', NULL, 160, NOW(), NOW(), NULL),
('Química', 2, 'BASE', NULL, 160, NOW(), NOW(), NULL),

-- Grade 3
('Português', 3, 'BASE', NULL, 200, NOW(), NOW(), NULL),
('Matemática', 3, 'BASE', NULL, 200, NOW(), NOW(), NULL),
('Biologia', 3, 'BASE', NULL, 160, NOW(), NOW(), NULL),
('Sociologia', 3, 'BASE', NULL, 120, NOW(), NOW(), NULL);



INSERT INTO subjects (
    name,
    grade,
    type,
    course,
    workload,
    created_at,
    updated_at,
    deleted_at
) VALUES
-- =========================
-- INFORMÁTICA
-- =========================
('Lógica de Programação', 1, 'COURSE_SPECIFIC', 'Informática', 200, NOW(), NOW(), NULL),
('Programação I', 1, 'COURSE_SPECIFIC', 'Informática', 200, NOW(), NOW(), NULL),

('Banco de Dados I', 2, 'COURSE_SPECIFIC', 'Informática', 160, NOW(), NOW(), NULL),
('Programação II', 2, 'COURSE_SPECIFIC', 'Informática', 200, NOW(), NOW(), NULL),

('Engenharia de Software', 3, 'COURSE_SPECIFIC', 'Informática', 160, NOW(), NOW(), NULL),
('Desenvolvimento Web', 3, 'COURSE_SPECIFIC', 'Informática', 200, NOW(), NOW(), NULL),


-- =========================
-- ENFERMAGEM
-- =========================
('Fundamentos de Enfermagem', 1, 'COURSE_SPECIFIC', 'Enfermagem', 200, NOW(), NOW(), NULL),
('Saúde Coletiva', 1, 'COURSE_SPECIFIC', 'Enfermagem', 160, NOW(), NOW(), NULL),

('Anatomia Humana', 2, 'COURSE_SPECIFIC', 'Enfermagem', 200, NOW(), NOW(), NULL),
('Farmacologia Básica', 2, 'COURSE_SPECIFIC', 'Enfermagem', 160, NOW(), NOW(), NULL),

('Enfermagem Clínica', 3, 'COURSE_SPECIFIC', 'Enfermagem', 200, NOW(), NOW(), NULL),
('Urgência e Emergência', 3, 'COURSE_SPECIFIC', 'Enfermagem', 160, NOW(), NOW(), NULL),


-- =========================
-- HOSPEDAGEM
-- =========================
('Introdução à Hotelaria', 1, 'COURSE_SPECIFIC', 'Hospedagem', 160, NOW(), NOW(), NULL),
('Atendimento ao Cliente', 1, 'COURSE_SPECIFIC', 'Hospedagem', 160, NOW(), NOW(), NULL),

('Gestão de Serviços', 2, 'COURSE_SPECIFIC', 'Hospedagem', 160, NOW(), NOW(), NULL),
('Governança', 2, 'COURSE_SPECIFIC', 'Hospedagem', 160, NOW(), NOW(), NULL),


-- =========================
-- MODELAGEM
-- =========================
('Modelagem Básica', 1, 'COURSE_SPECIFIC', 'Modelagem', 160, NOW(), NOW(), NULL),
('Desenho Técnico', 1, 'COURSE_SPECIFIC', 'Modelagem', 160, NOW(), NOW(), NULL),

('Modelagem Avançada', 2, 'COURSE_SPECIFIC', 'Modelagem', 160, NOW(), NOW(), NULL),
('Tecnologia dos Materiais', 2, 'COURSE_SPECIFIC', 'Modelagem', 160, NOW(), NOW(), NULL),

('Projeto de Produto', 3, 'COURSE_SPECIFIC', 'Modelagem', 200, NOW(), NOW(), NULL),
('Prototipagem', 3, 'COURSE_SPECIFIC', 'Modelagem', 160, NOW(), NOW(), NULL);


-- Grade 1 — Informática (enrollment_id = 1)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    1,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 1
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Informática')
    );


-- Grade 1 — Enfermagem (enrollment_id = 2)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    2,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 1
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Enfermagem')
    );


-- Grade 1 — Hospedagem (enrollment_id = 3)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    3,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 1
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Hospedagem')
    );

-- Grade 1 — Modelagem (enrollment_id = 4)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    4,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 1
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Modelagem')
    );

-- Grade 2 — Informática (enrollment_id = 5)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    5,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 2
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Informática')
    );


-- Grade 2 — Enfermagem (enrollment_id = 6)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    6,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 2
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Enfermagem')
    );


-- Grade 2 — Modelagem (enrollment_id = 7)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    7,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 2
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Modelagem')
    );


-- Grade 3 — Informática (enrollment_id = 8)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    8,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 3
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Informática')
    );


-- Grade 3 — Enfermagem (enrollment_id = 9)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    9,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 3
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Enfermagem')
    );


-- Grade 3 — Modelagem (enrollment_id = 10)
INSERT INTO grades (
    enrollment_id,
    subject_id,
    score,
    bimester,
    created_at,
    updated_at,
    deleted_at
)
SELECT
    10,
    s.id_subject,
    ROUND((RANDOM() * 3 + 6)::numeric, 2),
    b.bimester,
    NOW(),
    NOW(),
    NULL
FROM subjects s
         JOIN (
    SELECT 'BIMESTER_1' AS bimester UNION ALL
    SELECT 'BIMESTER_2' UNION ALL
    SELECT 'BIMESTER_3' UNION ALL
    SELECT 'BIMESTER_4'
) b ON TRUE
WHERE s.grade = 3
  AND (
    s.type = 'BASE'
        OR (s.type = 'COURSE_SPECIFIC' AND s.course = 'Modelagem')
    );

