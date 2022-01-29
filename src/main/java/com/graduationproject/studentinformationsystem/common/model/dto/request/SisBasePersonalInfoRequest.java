package com.graduationproject.studentinformationsystem.common.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graduationproject.studentinformationsystem.common.util.validation.*;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.DATE_PATTERN;

@Getter
public abstract class SisBasePersonalInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8140330376859399691L;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    protected Date birthday;

    @Address
    @NotNull
    protected String address;
}
