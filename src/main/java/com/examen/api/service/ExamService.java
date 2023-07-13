package com.examen.api.service;

import com.examen.api.models.emtity.*;

public interface ExamService {
    Exam createExam(Exam exam);
    Student createStudent(Student student);

    Student getStudentById(Long id);
    Exam getExamById(Long id);

    ExamAssignment assignmentExam(ExamAssignment assignment) throws Exception;

    ExamAssignment getAssignmentById(Long id);

    Answer saveAnswerStudent(Answer answers, Long idAssignment);

    Result gradeExam(Long idExam, Long idStudent);
}
