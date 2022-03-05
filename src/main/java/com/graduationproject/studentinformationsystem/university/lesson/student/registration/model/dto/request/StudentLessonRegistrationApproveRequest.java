package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentLessonRegistrationApproveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4277766662686555545L;

    @NotNull
    private String registrationId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
