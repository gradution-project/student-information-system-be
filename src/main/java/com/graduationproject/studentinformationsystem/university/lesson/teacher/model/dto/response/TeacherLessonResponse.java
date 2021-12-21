package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherLessonResponse {

    private Long teacherId;
    private Long lessonId;
    private Long departmentId;
    private String name;
    private Integer semester;
    private Integer credit;
    private String compulsoryOrElective;
    private String status;
}
