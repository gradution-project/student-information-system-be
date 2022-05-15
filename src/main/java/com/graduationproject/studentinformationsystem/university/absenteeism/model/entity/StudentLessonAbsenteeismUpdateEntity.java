package com.graduationproject.studentinformationsystem.university.absenteeism.model.entity;

import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class StudentLessonAbsenteeismUpdateEntity {

    private String id;
    private Integer theoreticalHours;
    private Integer practiceHours;
    private StudentLessonAbsenteeismStatus status;
    private Long modifiedUserId;
    private Date modifiedDate;
}
