package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.common.model.enums.SisStatus;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.enums.LessonCompulsoryOrElective;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LessonResponse extends SisBaseResponse {

    private Long id;
    private Long departmentId;
    private String name;
    private Integer semester;
    private Integer credit;
    private LessonCompulsoryOrElective compulsoryOrElective;
    private SisStatus status;
}
