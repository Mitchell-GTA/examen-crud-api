package com.examen.api.dao;

import com.examen.api.models.emtity.Exam;
import org.springframework.data.repository.CrudRepository;

public interface ExamDao extends CrudRepository<Exam,Long> {
}
