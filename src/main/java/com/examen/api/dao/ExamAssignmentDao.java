package com.examen.api.dao;

import com.examen.api.models.emtity.ExamAssignment;
import org.springframework.data.repository.CrudRepository;

public interface ExamAssignmentDao extends CrudRepository<ExamAssignment,Long> {
}
