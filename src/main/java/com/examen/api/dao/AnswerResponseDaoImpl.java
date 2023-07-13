package com.examen.api.dao;

import com.examen.api.models.emtity.AnswerRes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class AnswerResponseDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AnswerRes> obtnerRespuestas(Long idExam, Long idStudent){
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("obtenerRespuestas");
        query.setParameter("id_exam_in", idExam);
        query.setParameter("id_student_in", idStudent);
        query.execute();

        return query.getResultList();
    }
}
