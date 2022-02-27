package com.graduationproject.studentinformationsystem.login.officer.common.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseLoginInfoEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OfficerLoginInfoEntity extends SisBaseLoginInfoEntity {

    private Long officerId;
}
