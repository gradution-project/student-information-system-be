package com.graduationproject.studentinformationsystem.university.absenteeism.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentLessonAbsenteeismEntity extends SisBaseEntity {

    private String id;
    private Long teacherId;
    private Long studentId;
    private Long lessonId;
    private Integer week;
    private Integer theoreticalHours;
    private Integer practiceHours;
    private StudentLessonAbsenteeismStatus status;
}
