package com.graduationproject.studentinformationsystem.login.officer.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.OfficerID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerLoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8556507920660216619L;

    @NotNull
    @OfficerID
    private Long officerId;

    @NotNull
    private String password;
}
