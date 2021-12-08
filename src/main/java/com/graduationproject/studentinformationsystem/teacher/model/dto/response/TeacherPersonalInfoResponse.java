package com.graduationproject.studentinformationsystem.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TeacherPersonalInfoResponse extends SisBaseResponse {

    private Long teacherId;
    private Long tcNo;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private TeacherStatus status;
    //    private Byte[] profilePhoto; // TODO: Profile Photo
//    private String profilePhotoUrl; // TODO: Profile Photo URL
    private Date birthday;
    private String address;
}
