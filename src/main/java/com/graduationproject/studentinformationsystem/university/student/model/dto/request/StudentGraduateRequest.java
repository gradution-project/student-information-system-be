package com.graduationproject.studentinformationsystem.university.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
public class StudentGraduateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2101020666369093607L;

    @StudentID
    @NotNull
    private Long studentId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
