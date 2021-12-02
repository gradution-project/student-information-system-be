package com.graduationproject.studentinformationsystem.common.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class BasePersonalInfoEntity extends BaseEntity {

    private Long tcNo;
    private String name;
    private String surname;
    private String email;
    private Long phoneNumber;
    //    private Byte[] profilePhoto; // TODO: Profile Photo
//    private String profilePhotoUrl; // TODO: Profile Photo URL
    private Date birthday;
    private String address;
}
