package com.graduationproject.studentinformationsystem.teacher.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.BaseEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class TeacherPersonalInfoEntity extends BaseEntity {

    private Long teacherId;
    private Long tcNo;
    private String name;
    private String surname;
    private String email;
    private Long phoneNumber;
    private TeacherStatus status;
    //    private Byte[] profilePhoto; // TODO: Profile Photo
//    private String profilePhotoUrl; // TODO: Profile Photo URL
    private Date birthday;
    private String address;
}
