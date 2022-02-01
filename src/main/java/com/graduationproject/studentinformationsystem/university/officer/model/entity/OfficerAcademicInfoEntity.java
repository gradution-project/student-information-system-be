package com.graduationproject.studentinformationsystem.university.officer.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class OfficerAcademicInfoEntity extends SisBaseEntity {

    private Long officerId;
    private Long facultyId;
    private Long phoneNumber;
    private String email;
    private OfficerStatus status;
    private Date registrationDate;

    @Setter
    private FacultyEntity facultyEntity;
}
