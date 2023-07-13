package com.examen.api.dao;

import com.examen.api.models.emtity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionDao extends CrudRepository<Question,Long> {
}
