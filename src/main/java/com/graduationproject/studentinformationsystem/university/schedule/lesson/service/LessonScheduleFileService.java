package com.graduationproject.studentinformationsystem.university.schedule.lesson.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LessonScheduleFileService {

    List<ScheduleFileDetailResponse> getLessonScheduleFilesByFacultyId(Long facultyId)
            throws SisNotExistException;

    ScheduleFileResponse getLessonScheduleFileById(String fileId)
            throws SisNotExistException, IOException;

    ScheduleFileDetailResponse getLessonScheduleFileByDepartmentId(Long departmentId)
            throws SisNotExistException;

    ScheduleFileDetailResponse saveLessonScheduleFile(Long facultyId,
                                                      Long departmentId,
                                                      Long operationUserId,
                                                      MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException;

    void deleteLessonScheduleFileByDepartmentId(Long departmentId)
            throws SisNotExistException, SisAlreadyException;
}
