package com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.response;

import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Builder
public class ExamScheduleFileResponse {

    private String fileId;
    private MultipartFile pdf;
    private Long createdUserId;
    private Date createdDate;

    private DepartmentResponse departmentResponse;
}
