package com.examen.api.models.emtity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tastudentanswer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_answer")
    private Long idAnswer;

    @Column(name = "id_assignment")
    private Long idAssignment;

    @Column(name = "id_question")
    private Long idQuestion;

    @Column(name = "selected_optionid")
    private Long selectedOptionId;

    @Column(name = "create_at")
    private Date createAt;
}
