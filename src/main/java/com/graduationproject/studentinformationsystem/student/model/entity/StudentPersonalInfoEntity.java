package com.graduationproject.studentinformationsystem.student.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.BaseEntity;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class StudentPersonalInfoEntity extends BaseEntity {

    private Long studentId;
    private Long tcNo;
    private String name;
    private String surname;
    private String email;
    private Long phoneNumber;
    private StudentStatus status;
    //    private Byte[] profilePhoto; // TODO: Added Profile Photo
//    private String profilePhotoUrl; // TODO: Added Profile Photo URL
    private Date birthday;
    private String address;
}
