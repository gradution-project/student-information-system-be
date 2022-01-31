package com.graduationproject.studentinformationsystem.university.faculty.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.Name;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class FacultyInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3474421524096250857L;

    @NotNull
    @Name
    private String name;
}
