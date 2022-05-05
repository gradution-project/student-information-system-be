package com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.LessonID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class StudentLessonAbsenteeismSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6062678160042472379L;

    @NotNull
    @TeacherID
    private Long teacherId;

    @NotNull
    @StudentID
    private Long studentId;

    @NotNull
    @LessonID
    private Long lessonId;

    @NotNull
    private Integer week;

    @NotNull
    private Integer maxTheoreticalHours;

    @NotNull
    private Integer maxPracticeHours;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
