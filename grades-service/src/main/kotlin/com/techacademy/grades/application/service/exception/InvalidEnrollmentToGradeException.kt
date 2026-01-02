package com.techacademy.grades.application.service.exception

class InvalidEnrollmentToGradeException : RuntimeException(
    "A matrícula selecionada não pode receber essa nota! Verifique se informações da disciplina são compativeis com a turma da matrícula"
)