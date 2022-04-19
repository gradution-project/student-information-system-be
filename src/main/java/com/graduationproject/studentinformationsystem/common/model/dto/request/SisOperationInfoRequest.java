package com.graduationproject.studentinformationsystem.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.OperationUserID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class SisOperationInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4343452158117045893L;

    @NotNull
    @OperationUserID
    private Long userId;
}
