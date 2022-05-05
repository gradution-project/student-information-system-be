package com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
public class StudentsLessonAbsenteeismUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4786087456831453643L;

    @NotNull
    private Map<String, Map<String, Integer>> absenteeismIdsAndTheoreticalHoursAndPracticeHours;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
