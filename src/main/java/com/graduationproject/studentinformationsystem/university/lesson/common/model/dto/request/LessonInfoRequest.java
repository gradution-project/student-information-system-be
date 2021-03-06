package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.Name;
import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonCompulsoryOrElective;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonSemester;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class LessonInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8600275224416739167L;

    @DepartmentID
    @NotNull
    private Long departmentId;

    @Name
    @NotNull
    private String name;

    @NotNull
    private LessonSemester semester;

    @NotNull
    private Integer credit;

    @NotNull
    @Max(99)
    private Integer theoreticalHours;

    @NotNull
    @Max(99)
    private Integer practiceHours;

    @NotNull
    private LessonCompulsoryOrElective compulsoryOrElective;
}
