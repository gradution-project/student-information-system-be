package com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response;

import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleFileDetailResponse {

    private String fileId;
    private String fileName;
    private String fileType;
    private String fileDownloadUrl;
    private String fileViewUrl;
    private Long createdUserId;
    private String createdDate;

    private DepartmentResponse departmentResponse;
}
