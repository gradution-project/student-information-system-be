package com.graduationproject.studentinformationsystem.student.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentPersonalInfoResponse extends BaseResponse {

    private Long tcNo;
    private String name;
    private String surname;
    private String email;
    private Long phoneNumber;
    //    private Byte[] profilePhoto; // TODO: Added Profile Photo
//    private String profilePhotoUrl; // TODO: Added Profile Photo URL
    private Date birthday;
    private String address;
}
