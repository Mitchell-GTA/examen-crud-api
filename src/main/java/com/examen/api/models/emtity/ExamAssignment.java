package com.examen.api.models.emtity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "taexamassignment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamAssignment  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment")
    private Long idAssignment;

    @Column(name = "id_exam")
    private Long idExam;

    @Column(name = "id_student")
    private Long idStudent;

    @Transient
    private String dateExamCurrent;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "create_at")
    private Date createAt;
}
