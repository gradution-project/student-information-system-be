package com.graduationproject.studentinformationsystem.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.OnlyNumber;
import com.graduationproject.studentinformationsystem.common.util.validation.URL;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class StudentPersonalInfoRequestDto {

    @NotNull
    @OnlyNumber
    @Size(max = 11)
    private Long tcNo;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String surname;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @OnlyNumber
    @Size(max = 10)
    private Long phoneNumber;

    private byte[] profilePhoto;

    @URL
    private String profilePhotoUrl;

    @DateTimeFormat
    private Date birthday;

    @NotNull
    @Size(max = 256)
    private String address;
}
