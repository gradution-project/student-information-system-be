package com.graduationproject.studentinformationsystem.university.schedule.exam.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.response.ExamScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.response.ExamScheduleFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExamScheduleFileService {

    List<ExamScheduleFileDetailResponse> getExamScheduleFilesByFacultyId(Long facultyId)
            throws SisNotExistException;

    ExamScheduleFileResponse getExamScheduleFileById(String fileId)
            throws SisNotExistException, IOException;

    ExamScheduleFileDetailResponse getExamScheduleFileByDepartmentId(Long departmentId)
            throws SisNotExistException;

    ExamScheduleFileDetailResponse saveExamScheduleFile(String apiUrl,
                                                        Long facultyId,
                                                        Long departmentId,
                                                        Long operationUserId,
                                                        MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException;

    void deleteExamScheduleFileByDepartmentId(Long departmentId)
            throws SisNotExistException, SisAlreadyException;
}
