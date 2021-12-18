package com.graduationproject.studentinformationsystem.common.util.controller.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class SisBasePersonalInfoResponse extends SisBaseResponse {

    private Long tcNo;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    //    private Byte[] profilePhoto; // TODO: Added Profile Photo
//    private String profilePhotoUrl; // TODO: Added Profile Photo URL
    private String birthday;
    private String address;
}
