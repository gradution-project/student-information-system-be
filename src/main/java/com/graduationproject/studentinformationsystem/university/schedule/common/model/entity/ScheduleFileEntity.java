package com.graduationproject.studentinformationsystem.university.schedule.common.model.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Builder
public class ScheduleFileEntity {

    private String fileId;
    private Long facultyId;
    private Long departmentId;
    private String apiUrl;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private byte[] fileByte;
    private MultipartFile file;
    private Long createdUserId;
    private Date createdDate;
}
