package com.graduationproject.studentinformationsystem.university.faculty.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FacultyResponse extends SisBaseResponse {

    private Long facultyId;
    private String name;
    private String status;
}
