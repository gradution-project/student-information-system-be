package com.graduationproject.studentinformationsystem.common.util.controller.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class SisBasePersonalInfoResponse extends SisBaseResponse {

    protected Long tcNo;
    protected String name;
    protected String surname;
    protected String email;
    protected Long phoneNumber;
    //    protected Byte[] profilePhoto; // TODO: Added Profile Photo
//    protected String profilePhotoUrl; // TODO: Added Profile Photo URL
    protected Date birthday;
    protected String address;
}
