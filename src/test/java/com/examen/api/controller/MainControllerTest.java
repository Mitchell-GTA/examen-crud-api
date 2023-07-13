package com.examen.api.controller;

import com.examen.api.models.emtity.*;
import com.examen.api.service.ExamService;
import com.examen.api.utils.Constants;
import com.examen.api.utils.ExamScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MainControllerTest {

    @Mock
    private ExamService examService;

    @InjectMocks
    private MainController mainController;

    @InjectMocks
    private ExamScheduler scheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExam() {
        Exam exam = new Exam();
        exam.setCreateAt(new Date());
        when(examService.createExam(exam)).thenReturn(exam);

        //when(examService.createExam(exam)).thenThrow(new IllegalStateException("Test exception") {});
        assertNotNull(mainController.createExam(exam));

        when(examService.createExam(exam)).thenThrow(new DataAccessException("Test exception") {});
        ResponseEntity<?> responseEntity = mainController.createExam(exam);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Response response = (Response) responseEntity.getBody();
        assertEquals(Constants.MESSAGE_INSERT_ERROR, response.getMessage());

    }

    @Test
    void createStudent() {
        Student student = new Student();
        student.setCreateAt(new Date());
        when(examService.createStudent(student)).thenReturn(student);

        //when(examService.createStudent(student)).thenThrow(new IllegalStateException("Test exception") {});
        assertNotNull(mainController.createStudent(student));

        when(examService.createStudent(student)).thenThrow(new DataAccessException("Test exception") {});
        ResponseEntity<?> responseEntity = mainController.createStudent(student);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Response response = (Response) responseEntity.getBody();
        assertEquals(Constants.MESSAGE_INSERT_ERROR, response.getMessage());
    }

    @Test
    void assignExam() throws Exception {
        Long studentId = 1L;
        Long examId = 1L;
        ExamAssignment assignment = new ExamAssignment();
        assignment.setIdStudent(studentId);
        assignment.setIdExam(examId);
        assignment.setDateExamCurrent("2023-06-26 11:30");

        Student student = new Student();
        student.setTimezone("America/Bogota");
        Exam exam = new Exam();
        Date examDate = new Date();
        ExamAssignment examAssignment = new ExamAssignment();

        when(examService.getStudentById(studentId)).thenReturn(student);
        when(examService.getExamById(examId)).thenReturn(exam);
        when(examService.assignmentExam(Mockito.any(ExamAssignment.class))).thenReturn(examAssignment);

        assertNotNull(mainController.assignExam(assignment));


        when(examService.getStudentById(studentId)).thenThrow(new DataAccessException("Test exception") {});
        when(examService.getExamById(examId)).thenThrow(new DataAccessException("Test exception") {});
        when(examService.assignmentExam(Mockito.any(ExamAssignment.class))).thenThrow(new DataAccessException("Test exception") {});
        ResponseEntity<?> responseEntity = mainController.assignExam(assignment);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Response response = (Response) responseEntity.getBody();
        assertEquals(Constants.MESSAGE_INSERT_ERROR, response.getMessage());

    }

    @Test
    void collectAnswers() {
        Answer answer = new Answer();
        Long idAssignment = 8L;
        List<StudentAnswer> answerList = new ArrayList<>();
        ExamAssignment assignment = new ExamAssignment();
        when(examService.getAssignmentById(idAssignment)).thenReturn(assignment);

        assertNotNull(mainController.collectAnswers(answer,idAssignment));

        when(examService.getAssignmentById(idAssignment)).thenThrow(new DataAccessException("Test exception") {});
        ResponseEntity<?> responseEntity = mainController.collectAnswers(answer,idAssignment);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Response response = (Response) responseEntity.getBody();
        assertEquals(Constants.MESSAGE_INSERT_ERROR, response.getMessage());
    }

    @Test
    void gradeExam() {
        Long idExam = 6L;
        Long idStudent = 2L;
        Result result = new Result();
        when(examService.gradeExam(idExam, idStudent)).thenReturn(result);
        assertNotNull(mainController.gradeExam(idExam,idStudent));

        when(examService.gradeExam(idExam,idStudent)).thenThrow(new DataAccessException("Test exception") {});
        ResponseEntity<?> responseEntity = mainController.gradeExam(idExam,idStudent);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Response response = (Response) responseEntity.getBody();
        assertEquals(Constants.MESSAGE_INSERT_ERROR, response.getMessage());

    }
}