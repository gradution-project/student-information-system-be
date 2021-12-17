package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.enums.SisStatus;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.enums.LessonCompulsoryOrElective;
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
    private LessonCompulsoryOrElective compulsoryOrElective;
    private SisStatus status;
}
