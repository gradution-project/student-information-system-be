package com.graduationproject.studentinformationsystem.university.lesson.common.model.entity;

import com.graduationproject.studentinformationsystem.common.model.enums.SisStatus;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.enums.LessonCompulsoryOrElective;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LessonEntity {

    private Long id;
    private Long departmentId;
    private String name;
    private Integer semester;
    private Integer credit;
    private LessonCompulsoryOrElective compulsoryOrElective;
    private SisStatus status;
}
