package com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FileID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class ExamScheduleFileDeleteRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -8134341752924913437L;

    @FileID
    @NotNull
    private String fileId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
