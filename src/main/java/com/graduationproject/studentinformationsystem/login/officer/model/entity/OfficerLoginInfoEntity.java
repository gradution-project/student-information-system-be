package com.graduationproject.studentinformationsystem.login.officer.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseLoginEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficerLoginInfoEntity extends SisBaseLoginEntity {

    private Long officerId;
}
