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
public class StudentsLessonMidtermNotesUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5142587290257640755L;

    @NotNull
    @NotEmpty
    private Map<String, Double> midtermNoteIdsAndNotes;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
