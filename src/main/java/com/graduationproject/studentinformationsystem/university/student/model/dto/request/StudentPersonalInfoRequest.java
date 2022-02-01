package com.graduationproject.studentinformationsystem.university.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisBasePersonalInfoRequest;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentPersonalInfoRequest extends SisBasePersonalInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4027525668413696404L;
}
