package com.examen.api.dao;

import com.examen.api.models.emtity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student,Long> {
}
