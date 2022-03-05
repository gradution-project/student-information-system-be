package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentLessonRegistrationRejectRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2404839697727479952L;

    @NotNull
    private String registrationId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
