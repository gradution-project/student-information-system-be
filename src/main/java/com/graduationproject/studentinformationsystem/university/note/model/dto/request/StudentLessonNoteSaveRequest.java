package com.graduationproject.studentinformationsystem.university.note.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.LessonID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@SuperBuilder
public class StudentLessonNoteSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7246638446202788931L;

    @NotNull
    @TeacherID
    private Long teacherId;

    @NotNull
    @StudentID
    private Long studentId;

    @NotNull
    @LessonID
    private Long lessonId;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
