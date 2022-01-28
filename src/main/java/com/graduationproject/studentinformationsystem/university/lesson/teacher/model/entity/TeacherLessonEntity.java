package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity;

import com.graduationproject.studentinformationsystem.common.model.enums.SisStatus;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonCompulsoryOrElective;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherLessonEntity {

    private Long teacherId;
    private Long lessonId;
    private Long departmentId;
    private String name;
    private Integer semester;
    private Integer credit;
    private LessonCompulsoryOrElective compulsoryOrElective;
    private SisStatus status;
    private Long createdUserId;
    private String createdDate;
}
