package com.graduationproject.studentinformationsystem.university.note.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@SuperBuilder
@NoArgsConstructor
public class StudentsLessonResitNotesUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4495180748614571012L;

    @NotNull
    @NotEmpty
    private Map<String, Double> resitNoteIdsAndNotes;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
