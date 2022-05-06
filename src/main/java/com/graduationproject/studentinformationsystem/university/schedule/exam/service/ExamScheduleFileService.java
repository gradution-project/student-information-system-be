package com.graduationproject.studentinformationsystem.university.schedule.exam.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExamScheduleFileService {

    List<ScheduleFileDetailResponse> getExamScheduleFilesByFacultyId(Long facultyId)
            throws SisNotExistException;

    ScheduleFileResponse getExamScheduleFileById(String fileId)
            throws SisNotExistException, IOException;

    ScheduleFileDetailResponse getExamScheduleFileByDepartmentId(Long departmentId)
            throws SisNotExistException;

    ScheduleFileDetailResponse saveExamScheduleFile(Long facultyId,
                                                    Long departmentId,
                                                    Long operationUserId,
                                                    MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException;

    void deleteExamScheduleFileByDepartmentId(Long departmentId)
            throws SisNotExistException, SisAlreadyException;
}
