package com.graduationproject.studentinformationsystem.student.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.BaseEntity;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class StudentAcademicInfoEntity extends BaseEntity {

    private Long studentId;
    private Long departmentId;
    private StudentDegree degree;
    private StudentClassLevel classLevel;
    private String email;
    private StudentStatus status;
    private Date registrationDate;
}
