package com.graduationproject.studentinformationsystem.university.note.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.LessonID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class StudentsLessonNoteStatusUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 9055593920295060907L;

    @NotNull
    @StudentID
    private Long studentId;

    @NotNull
    @LessonID
    private Long lessonId;

    @NotNull
    private StudentLessonNoteStatus status;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
