package com.examen.api.models.emtity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "taquestion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Long idQuestion;

    @Column(name = "id_exam")
    private Long idExam;

    private String question;

    @Transient
    private List<AnswerOption> options;

    @Column(name = "create_at")
    private Date createAt;

}
