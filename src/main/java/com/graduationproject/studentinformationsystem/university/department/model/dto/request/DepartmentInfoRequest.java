package com.graduationproject.studentinformationsystem.university.department.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.Name;
import com.graduationproject.studentinformationsystem.common.util.validation.OneOrZero;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FacultyID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class DepartmentInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3837248019724047650L;

    @FacultyID
    @NotNull
    private Long facultyId;

    @Name
    @NotNull
    private String name;

    @NotNull
    private Integer totalClassLevel;

    @OneOrZero
    @NotNull
    private Integer isTherePreparatoryClass;
}
