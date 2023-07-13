package com.examen.api.models.emtity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "obtenerRespuestas",
        procedureName = "FN_OBTENER_RESPUESTAS",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_exam_in", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "id_student_in", type = Long.class)
        },
        resultClasses = AnswerRes.class
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRes {
    @Id
    private Long idQuestion;
    private String question;
    private Long idOption;
    private String optionText;
    private Long selected_optionid;
    private String response;
}
