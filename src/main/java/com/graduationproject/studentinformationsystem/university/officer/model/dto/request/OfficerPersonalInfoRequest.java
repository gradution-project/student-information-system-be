package com.graduationproject.studentinformationsystem.university.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisBasePersonalInfoRequest;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerPersonalInfoRequest extends SisBasePersonalInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 2088059305420267979L;
}
