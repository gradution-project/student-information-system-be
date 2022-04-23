package com.graduationproject.studentinformationsystem.university.note.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@SuperBuilder
@NoArgsConstructor
public class StudentsLessonFinalNotesUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5093920385984058988L;

    @NotNull
    private Map<String, Double> finalNoteIdsAndNotes;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
