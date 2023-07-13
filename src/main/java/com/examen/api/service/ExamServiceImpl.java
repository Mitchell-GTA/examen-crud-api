package com.examen.api.service;

import com.examen.api.dao.*;
import com.examen.api.models.emtity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao examDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AnswerOptionDao answerOptionDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExamAssignmentDao assignmentDao;
    @Autowired
    private StudentAnswerDao studentAnswerDao;
    @Autowired
    private AnswerResponseDaoImpl answerResponseDaoImpl;
    @Override
    public Exam createExam(Exam exam) {
        Exam resExam = examDao.save(exam);
        for(Question question: exam.getQuestions()){
            question.setIdExam(resExam.getIdExam());
            question.setCreateAt(new Date());
            Question resQuestion = questionDao.save(question);
            for (AnswerOption options: resQuestion.getOptions()){
                options.setIdQuestion(question.getIdQuestion());
                options.setCreateAt(new Date());
                AnswerOption resOption = answerOptionDao.save(options);
            }
        }
        System.out.println("resExam = " + resExam);
        return resExam;
    }

    @Override
    public Student createStudent(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.findById(id).orElse(null);
    }

    @Override
    public Exam getExamById(Long id) {
        return examDao.findById(id).orElse(null);
    }

    @Override
    public ExamAssignment assignmentExam(ExamAssignment assignment) {
        return assignmentDao.save(assignment);
    }

    @Override
    public ExamAssignment getAssignmentById(Long id) {
        return assignmentDao.findById(id).orElse(null);
    }

    @Override
    public Answer saveAnswerStudent(Answer answers, Long idAssignment) {
        for (StudentAnswer answer: answers.getAnswers()){
            answer.setIdAssignment(idAssignment);
            answer.setCreateAt(new Date());
            studentAnswerDao.save(answer);
        }
        System.out.println("answers = " + answers);
        return answers;
    }

    @Override
    public Result gradeExam(Long idExam, Long idStudent) {
        int totalPuntos = 100;
        int totalTrue = 0;
        int totalQuestion;

        List<AnswerRes> answerRes = answerResponseDaoImpl.obtnerRespuestas(idExam,idStudent);
        System.out.println("answerRes = " + answerRes);
        for (int i = 0; i < answerRes.size(); i++) {
            if (answerRes.get(i).getResponse().equals("TRUE")){
                totalTrue++;
            }
        }
        totalQuestion = answerRes.size();
        int calif = (totalPuntos/totalQuestion) * totalTrue;
        Result res = new Result(totalQuestion,totalTrue,calif);
        System.out.println("calif = " + calif);
        System.out.println("totalTrue = " + totalTrue);

        return res;
    }

}
