package com.graduationproject.studentinformationsystem.university.officer.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBasePersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OfficerPersonalInfoEntity extends SisBasePersonalInfoEntity {

    private Long officerId;
    private OfficerStatus status;
}
