package com.examen.api.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExamSchedulerTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void scheduleExamForStudent() throws ParseException {
        String studentTimeZone = "America/New_York";
        String dateString = "2023-06-28 10:00";
        Date expectedStudentExamDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2023-06-28 11:00");

        // Act
        //Date actualStudentExamDate = ExamScheduler.scheduleExamForStudent(studentTimeZone, dateString);

        // Assert
        assertNotNull(ExamScheduler.scheduleExamForStudent(studentTimeZone, dateString));
    }
}