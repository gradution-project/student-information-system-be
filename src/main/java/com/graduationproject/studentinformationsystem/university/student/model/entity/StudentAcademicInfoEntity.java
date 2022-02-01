package com.graduationproject.studentinformationsystem.university.student.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class StudentAcademicInfoEntity extends SisBaseEntity {

    private Long studentId;
    private Long departmentId;
    private StudentDegree degree;
    private StudentClassLevel classLevel;
    private String email;
    private StudentStatus status;
    private Date registrationDate;

    @Setter
    private DepartmentEntity departmentEntity;
}
