package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisBasePersonalInfoRequest;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherPersonalInfoRequest extends SisBasePersonalInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2109265391122063272L;
}
