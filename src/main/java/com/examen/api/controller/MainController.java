package com.examen.api.controller;

import com.examen.api.models.emtity.*;
import com.examen.api.service.ExamService;
import com.examen.api.utils.Constants;
import com.examen.api.utils.ExamScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ExamService examService;

    @PostMapping("/exams")
    public ResponseEntity<?> createExam(@RequestBody Exam exam) {
        exam.setCreateAt(new Date());
        Exam reqExam;
        Response response;
        try {
            reqExam = examService.createExam(exam);
            response = new Response(Constants.MESSAGE_INSERT,reqExam,HttpStatus.CREATED);
        }catch (DataAccessException e){
            response = new Response(Constants.MESSAGE_INSERT_ERROR,
                    "Error ".concat(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        student.setCreateAt(new Date());
        Student saveStudent;
        Response response;
        try {
            saveStudent = examService.createStudent(student);
            response = new Response(Constants.MESSAGE_INSERT,saveStudent,HttpStatus.CREATED);

        }catch (DataAccessException e){
            response = new Response(Constants.MESSAGE_INSERT_ERROR,
                    "Error ".concat(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())),HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping("/exams/assign")
    public ResponseEntity<?> assignExam(@RequestBody ExamAssignment assignment) {
        Response response;
        ExamAssignment examAssignment = new ExamAssignment();
        try {
            Student student = examService.getStudentById(assignment.getIdStudent());
            if (student == null){
                response = new Response(Constants.MESSAGE_NO_EXIST,
                        "El estudiante no existe",HttpStatus.INTERNAL_SERVER_ERROR);
            }else {
                Exam exam = examService.getExamById(assignment.getIdExam());
                if (exam == null){
                    response = new Response(Constants.MESSAGE_NO_EXIST,"El examen no existe",HttpStatus.INTERNAL_SERVER_ERROR);
                }else {
                    assignment.setCreateAt(new Date());
                    Date examDate =  ExamScheduler.scheduleExamForStudent(student.getTimezone(),assignment.getDateExamCurrent());
                    assignment.setExamDate(examDate);
                    examAssignment = examService.assignmentExam(assignment);
                    response = new Response(Constants.MESSAGE_INSERT,examAssignment,HttpStatus.ACCEPTED);
                }
            }

        }catch (DataAccessException e){
            response = new Response(Constants.MESSAGE_INSERT_ERROR,
                    "Error ".concat(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/exams/{idAssignment}/students/answers")
    public ResponseEntity<?> collectAnswers(@RequestBody Answer answer,
                                            @PathVariable("idAssignment") Long idAssignment){
        Response response;
        try {
            ExamAssignment assignment = examService.getAssignmentById(idAssignment);
            if (assignment == null) {
                response = new Response(Constants.MESSAGE_NO_EXIST,
                        "La asignación no existe",HttpStatus.INTERNAL_SERVER_ERROR);
            }else {
                Answer answerNew = examService.saveAnswerStudent(answer, idAssignment);
                response = new Response(Constants.MESSAGE_INSERT,answerNew,HttpStatus.ACCEPTED);
            }

        }catch (DataAccessException e){
            response = new Response(Constants.MESSAGE_INSERT_ERROR,
                    "Error ".concat(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,response.getStatus());
    }

    @GetMapping("/exams/{examId}/students/{studentId}/grade")
    public ResponseEntity<?> gradeExam(
            @PathVariable("examId") Long examId,
            @PathVariable("studentId") Long studentId) {
        Response response;
        try {
            Result res = examService.gradeExam(examId,studentId);
            response = new Response(Constants.MESSAGE_INSERT,res,HttpStatus.ACCEPTED);
        }catch (DataAccessException e){
            response = new Response(Constants.MESSAGE_INSERT_ERROR,
                    "Error ".concat(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,response.getStatus());
    }

}
