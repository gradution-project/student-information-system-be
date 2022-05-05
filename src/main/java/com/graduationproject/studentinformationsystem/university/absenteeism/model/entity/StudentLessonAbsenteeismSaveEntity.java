package com.graduationproject.studentinformationsystem.university.absenteeism.model.entity;

import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class StudentLessonAbsenteeismSaveEntity {

    private String id;
    private Long teacherId;
    private Long studentId;
    private Long lessonId;
    private Integer week;
    private Integer maxTheoreticalHours;
    private Integer maxPracticeHours;
    private StudentLessonAbsenteeismStatus status;
    private Long createdUserId;
    private Date createdDate;
}
