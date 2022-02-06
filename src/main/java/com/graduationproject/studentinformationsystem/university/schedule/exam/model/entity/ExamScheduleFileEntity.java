package com.graduationproject.studentinformationsystem.university.schedule.exam.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Builder
public class ExamScheduleFileEntity {

    private String fileId;
    private Long departmentId;
    private MultipartFile pdf;
    private Long createdUserId;
    private Date createdDate;
}
