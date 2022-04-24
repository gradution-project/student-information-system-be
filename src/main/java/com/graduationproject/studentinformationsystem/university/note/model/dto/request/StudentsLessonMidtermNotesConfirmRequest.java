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
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
public class StudentsLessonMidtermNotesConfirmRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3615576834979563499L;

    @NotNull
    @NotEmpty
    private List<String> lessonNoteIds;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}