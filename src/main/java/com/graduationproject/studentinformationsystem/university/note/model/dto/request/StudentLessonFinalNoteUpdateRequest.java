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
public class StudentLessonFinalNoteUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1731196715166066519L;

    @NotNull
    private String id;

    @NotNull
    private Double finalNote;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
