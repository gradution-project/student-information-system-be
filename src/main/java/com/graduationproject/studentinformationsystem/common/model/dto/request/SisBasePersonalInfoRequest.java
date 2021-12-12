package com.graduationproject.studentinformationsystem.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public abstract class SisBasePersonalInfoRequest {

    @TCNO
    @NotNull
    protected Long tcNo;

    @NotNull
    @Name
    protected String name;

    @NotNull
    @Surname
    protected String surname;

    @Email
    @NotNull
    protected String email;

    @NotNull
    @PhoneNumber
    protected Long phoneNumber;

//    protected Byte[] profilePhoto; // TODO: Added Profile Photo

//    @URL
//    protected String profilePhotoUrl; // TODO: Added Profile Photo URL

    @NotNull
    @DateTimeFormat
    protected Date birthday;

    @Address
    @NotNull
    protected String address;
}
