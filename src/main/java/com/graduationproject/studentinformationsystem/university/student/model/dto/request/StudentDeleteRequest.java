package com.graduationproject.studentinformationsystem.university.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentDeleteRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3161653514362419722L;

    @StudentID
    @NotNull
    private Long studentId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
