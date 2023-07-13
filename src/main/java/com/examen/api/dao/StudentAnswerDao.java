package com.examen.api.dao;

import com.examen.api.models.emtity.StudentAnswer;
import org.springframework.data.repository.CrudRepository;

public interface StudentAnswerDao extends CrudRepository<StudentAnswer,Long> {
}
