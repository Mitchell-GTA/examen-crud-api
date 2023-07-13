package com.examen.api.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ExamScheduler {

    public static Date scheduleExamForStudent(String studentTimeZone, String dateString) throws ParseException {
        // Información del estudiante
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date examDate = sdf.parse(dateString);

        // Convertir la fecha del examen a LocalDateTime
        LocalDateTime examDateTime = convertToLocalDateTime(examDate);

        // Calcular fecha de presentación del examen para el estudiante
        Date studentExamDateTime = calculateStudentExamDate(examDateTime, studentTimeZone);



        // Mostrar la fecha de presentación del examen para el estudiante
        System.out.println("Fecha de presentación del examen para el estudiante: " + studentExamDateTime);
        return studentExamDateTime;
    }

    private static LocalDateTime convertToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date calculateStudentExamDate(LocalDateTime examDateTime, String studentTimeZone) {
        // Obtén la zona horaria del estudiante
        ZoneId studentTimeZoneId = ZoneId.of(studentTimeZone);

        // Obtén la zona horaria de Bogotá
        ZoneId bogotaTimeZone = ZoneId.of("America/Bogota");

        // Convierte la fecha y hora del examen a la zona horaria del estudiante
        ZonedDateTime examDateTimeInStudentTimeZone = examDateTime.atZone(bogotaTimeZone).withZoneSameInstant(studentTimeZoneId);

        // Convierte ZonedDateTime a Date
        Date studentExamDate = Date.from(examDateTimeInStudentTimeZone.toInstant());

        return studentExamDate;
    }


}
