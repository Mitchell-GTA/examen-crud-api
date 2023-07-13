package com.examen.api.models.emtity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tastudent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long studentId;

    @Column(name = "name_student",nullable = false)
    private String studentName;

    private int age;

    private String city;

    private String timezone;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
}
