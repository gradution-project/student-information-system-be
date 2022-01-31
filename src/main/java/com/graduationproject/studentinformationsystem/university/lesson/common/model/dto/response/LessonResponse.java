package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LessonResponse extends SisBaseResponse {

    private Long lessonId;
    private Long departmentId;
    private String name;
    private String status;
    private String semester;
    private Integer credit;
    private String compulsoryOrElective;
}
