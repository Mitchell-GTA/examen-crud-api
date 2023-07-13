create table if not exists taexam
(
    id_exam     bigint auto_increment
        primary key,
    exam_name   varchar(250) null,
    total_score int          null,
    create_at   date         null
);

create table if not exists taquestion
(
    id_question bigint auto_increment
        primary key,
    id_exam     bigint       null,
    question    varchar(250) null,
    create_at   date         null,
    constraint taquestion_ibfk_1
        foreign key (id_exam) references taexam (id_exam)
);

create table if not exists taansweroption
(
    id_option   bigint auto_increment
        primary key,
    id_question bigint       null,
    option_text varchar(250) null,
    is_correct  int          null,
    create_at   date         null,
    constraint taansweroption_ibfk_1
        foreign key (id_question) references taquestion (id_question)
);

create index id_question
    on taansweroption (id_question);

create index id_exam
    on taquestion (id_exam);

create table if not exists tastudent
(
    id_student   bigint auto_increment
        primary key,
    name_student varchar(250) null,
    age          int          null,
    city         varchar(100) null,
    timezone     varchar(50)  null,
    create_at    date         null
);

create table if not exists taexamassignment
(
    id_assignment bigint auto_increment
        primary key,
    id_exam       bigint   null,
    id_student    bigint   null,
    exam_date     datetime null,
    create_at     date     null,
    constraint taexamassignment_ibfk_1
        foreign key (id_exam) references taexam (id_exam),
    constraint taexamassignment_ibfk_2
        foreign key (id_student) references tastudent (id_student)
);

create index id_exam
    on taexamassignment (id_exam);

create index id_student
    on taexamassignment (id_student);

create table if not exists tastudentanswer
(
    id_answer         bigint auto_increment
        primary key,
    id_assignment     bigint null,
    id_question       bigint null,
    selected_optionId int    null,
    create_at         date   null,
    constraint tastudentanswer_ibfk_1
        foreign key (id_assignment) references taexamassignment (id_assignment),
    constraint tastudentanswer_ibfk_2
        foreign key (id_question) references taquestion (id_question)
);

create index id_assignment
    on tastudentanswer (id_assignment);

create index id_question
    on tastudentanswer (id_question);

create
    definer = root@localhost procedure FN_OBTENER_RESPUESTAS(IN id_exam_in int, IN id_student_in int)
BEGIN
    SELECT TQ.id_question,
           TQ.question,
           TAO.id_option,
           TAO.option_text,
           TS.selected_optionId,
           CASE
               WHEN TAO.id_option = TS.selected_optionId THEN 'TRUE'
               ELSE 'FALSE'
           END AS response
    FROM TAEXAMASSIGNMENT TEA
    INNER JOIN TAQUESTION TQ ON TEA.id_exam = TQ.id_exam
    INNER JOIN TAANSWEROPTION TAO ON TQ.id_question = TAO.id_question AND TAO.is_correct = 1
    INNER JOIN TASTUDENTANSWER TS ON TQ.id_question = TS.id_question
    WHERE TEA.id_exam = id_exam_in AND TEA.id_student = id_student_in;
END;

