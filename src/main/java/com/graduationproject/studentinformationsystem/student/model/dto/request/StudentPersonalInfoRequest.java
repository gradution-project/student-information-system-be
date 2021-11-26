package com.graduationproject.studentinformationsystem.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.group.OnCreate;
import com.graduationproject.studentinformationsystem.common.util.validation.OnlyNumber;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class StudentPersonalInfoRequest {

    @OnlyNumber
    @Size(max = 11)
    @NotNull(groups = {OnCreate.class})
    private Long tcNo;

    @Size(max = 100)
    @NotNull(groups = {OnCreate.class})
    private String name;

    @Size(max = 100)
    @NotNull(groups = {OnCreate.class})
    private String surname;

    @Email
    @Size(max = 100)
    @NotNull(groups = {OnCreate.class})
    private String email;

    @OnlyNumber
    @Size(max = 10)
    @NotNull(groups = {OnCreate.class})
    private Long phoneNumber;

//    private Byte[] profilePhoto; // TODO: Added Profile Photo

//    @URL
//    private String profilePhotoUrl; // TODO: Added Profile Photo URL

    @DateTimeFormat
    @NotNull(groups = {OnCreate.class})
    private Date birthday;

    @Size(max = 256)
    @NotNull(groups = {OnCreate.class})
    private String address;
}
