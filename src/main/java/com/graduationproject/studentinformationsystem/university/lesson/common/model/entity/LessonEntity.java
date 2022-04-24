package com.graduationproject.studentinformationsystem.university.lesson.common.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonCompulsoryOrElective;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonSemester;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LessonEntity extends SisBaseEntity {

    private Long lessonId;
    private Long departmentId;
    private String name;
    private LessonStatus status;
    private LessonSemester semester;
    private Integer credit;
    private Integer theoreticalHours;
    private Integer practiceHours;
    private LessonCompulsoryOrElective compulsoryOrElective;
}
