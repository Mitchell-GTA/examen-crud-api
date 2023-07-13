package com.examen.api.models.emtity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "taansweroption")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_option")
    private Long idOption;

    @Column(name = "id_question")
    private Long idQuestion;

    @Column(name = "option_text")
    private String optionText;

    @Column(name = "is_correct")
    private int isCorrect;

    @JsonIgnore
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
}
