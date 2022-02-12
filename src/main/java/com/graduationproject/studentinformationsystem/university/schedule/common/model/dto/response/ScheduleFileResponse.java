package com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class ScheduleFileResponse {

    private String fileName;
    private byte[] fileByte;
    private Long fileSize;
    private MultipartFile file;
}
