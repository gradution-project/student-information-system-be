package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonCompulsoryOrElective;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonSemester;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LessonResponse extends SisBaseResponse {

    private Long lessonId;
    private String name;
    private LessonStatus status;
    private LessonSemester semester;
    private Integer credit;
    private LessonCompulsoryOrElective compulsoryOrElective;

    private DepartmentResponse departmentResponse;
}
