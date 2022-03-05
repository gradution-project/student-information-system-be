package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentLessonSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7325837862878819492L;

    @Valid
    @NotNull
    private StudentLessonInfoRequest studentLessonInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
