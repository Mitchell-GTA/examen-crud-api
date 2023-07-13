package com.examen.api.models.emtity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taexam")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exam")
    private Long idExam;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "total_score")
    private int totalScore;

    @Transient
    private List<Question> questions;

    @Column(name = "create_at")
    private Date createAt;
}
