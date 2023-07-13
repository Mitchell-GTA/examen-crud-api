package com.examen.api.service;

import com.examen.api.dao.*;
import com.examen.api.models.emtity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExamServiceImplTest {

    @Mock
    private ExamDao examDao;
    @Mock
    private QuestionDao questionDao;
    @Mock
    private AnswerOptionDao answerOptionDao;
    @Mock
    private StudentDao studentDao;
    @Mock
    private ExamAssignmentDao assignmentDao;
    @Mock
    private StudentAnswerDao studentAnswerDao;
    @Mock
    private AnswerResponseDaoImpl answerResponseDaoImpl;

    @InjectMocks
    private ExamServiceImpl examService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExam() {
        Exam exam = new Exam();
        List<Question> questions = new ArrayList<>();
        List<AnswerOption>  answers = new ArrayList<>();
        answers.add(new AnswerOption(1L,2L,"Opcion 1",1,new Date()));
        questions.add(new Question(1L,1L,"",answers,new Date()));
        exam.setIdExam(1L);
        exam.setExamName("Demo example");
        exam.setTotalScore(100);
        exam.setCreateAt(new Date());
        exam.setQuestions(questions);
        when(examDao.save(exam)).thenReturn(exam);
        when(questionDao.save(questions.get(0))).thenReturn(questions.get(0));
        assertNotNull(examService.createExam(exam));

    }

    @Test
    void createStudent() {
        when(studentDao.save(new Student())).thenReturn(new Student());
        assertNotNull(examService.createStudent(new Student()));
    }

    @Test
    void getStudentById() {
        Student student = new Student(1L,"John",26,"Bogotá","America/Bogota",new Date());
        when(studentDao.findById(1L)).thenReturn(Optional.of(student));
        assertNotNull(examService.getStudentById(1L));
    }

    @Test
    void getExamById() {
        when(examDao.findById(1L)).thenReturn(Optional.of(new Exam()));
        assertNotNull(examService.getExamById(1L));
    }

    @Test
    void assignmentExam() {
        when(assignmentDao.save(new ExamAssignment())).thenReturn(new ExamAssignment());
        assertNotNull(examService.assignmentExam(new ExamAssignment()));
    }

    @Test
    void getAssignmentById() {
        when(assignmentDao.findById(1L)).thenReturn(Optional.of(new ExamAssignment()));
        assertNotNull(examService.getAssignmentById(1L));
    }

    @Test
    void saveAnswerStudent() {
        Answer answers = new Answer();
        List<StudentAnswer> list = new ArrayList<>();
        list.add(new StudentAnswer(1L,1L,2L,2L,new Date()));
        answers.setAnswers(list);
        for (StudentAnswer answer: answers.getAnswers()){
            when(studentAnswerDao.save(answer)).thenReturn(new StudentAnswer());
        }
        assertNotNull(examService.saveAnswerStudent(answers,1L));
    }

    @Test
    void gradeExam() {
        List<AnswerRes> answers = new ArrayList<>();
        answers.add(new AnswerRes(16L,"Pregunta 1. ¿Que es programacion orientada a objectos?",64L,"D. Opción 1",63L,"FALSE"));
        when(answerResponseDaoImpl.obtnerRespuestas(6L,2L)).thenReturn(answers);
        examService.gradeExam(6L,2L);
    }
}