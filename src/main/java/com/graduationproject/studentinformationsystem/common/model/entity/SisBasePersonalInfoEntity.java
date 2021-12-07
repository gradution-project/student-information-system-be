package com.graduationproject.studentinformationsystem.common.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public abstract class SisBasePersonalInfoEntity extends SisBaseEntity {

    protected Long tcNo;
    protected String name;
    protected String surname;
    protected String email;
    protected Long phoneNumber;
    //    protected Byte[] profilePhoto; // TODO: Profile Photo
//    protected String profilePhotoUrl; // TODO: Profile Photo URL
    protected Date birthday;
    protected String address;
}
