package com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.PDF;
import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class ExamScheduleFileSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5073057520074044437L;

    @DepartmentID
    @NotNull
    private Long departmentId;

    @PDF
    @NotNull
    private MultipartFile pdf;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
