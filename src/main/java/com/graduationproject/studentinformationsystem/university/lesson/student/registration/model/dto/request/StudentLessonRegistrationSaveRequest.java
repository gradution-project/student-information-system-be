package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentLessonRegistrationSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1832299531569396048L;

    @Valid
    @NotNull
    private StudentLessonRegistrationInfoRequest studentLessonRegistrationInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
