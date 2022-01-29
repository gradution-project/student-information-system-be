package com.graduationproject.studentinformationsystem.university.faculty.model.dto.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.enums.FacultyStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FacultyEntity extends SisBaseEntity {

    private Long facultyId;
    private String name;
    private FacultyStatus status;
}
