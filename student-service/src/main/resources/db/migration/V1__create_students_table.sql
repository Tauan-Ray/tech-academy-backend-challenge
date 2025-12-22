CREATE TABLE students (
   id_student SERIAL PRIMARY KEY,
   name VARCHAR(60) NOT NULL,
   email VARCHAR(255) NOT NULL UNIQUE,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL,
   deleted_at TIMESTAMP
);
