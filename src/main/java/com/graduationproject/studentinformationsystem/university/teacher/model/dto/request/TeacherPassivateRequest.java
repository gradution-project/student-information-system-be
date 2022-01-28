package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherPassivateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2609790431041403588L;

    @TeacherID
    @NotNull
    private Long teacherId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
