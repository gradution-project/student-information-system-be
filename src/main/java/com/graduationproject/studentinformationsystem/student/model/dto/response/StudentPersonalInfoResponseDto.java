package com.graduationproject.studentinformationsystem.student.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.BaseResponseDto;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentPersonalInfoResponseDto extends BaseResponseDto {

    private Long studentId;
    private Long tcNo;
    private String name;
    private String surname;
    private String email;
    private Long phoneNumber;
    private StudentStatus status;
    private byte[] profilePhoto;
    private String profilePhotoUrl;
    private Date birthday;
    private String address;
}
