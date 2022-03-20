package com.graduationproject.studentinformationsystem.university.note.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
@SuperBuilder
@NoArgsConstructor
public class StudentLessonResitNoteUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -6303107915064579379L;

    @NotNull
    private String id;

    @NotNull
    private Double resitNote;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
