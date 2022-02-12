package com.graduationproject.studentinformationsystem.university.schedule.exam.repository;

import com.graduationproject.studentinformationsystem.university.schedule.exam.model.entity.ExamScheduleFileEntity;

import java.util.List;

public interface ExamScheduleFileRepository {

    List<ExamScheduleFileEntity> getExamScheduleFilesByFacultyId(Long facultyId);

    ExamScheduleFileEntity getExamScheduleFileById(String fileId);

    ExamScheduleFileEntity getExamScheduleFileByDepartmentId(Long departmentId);

    void saveExamScheduleFile(ExamScheduleFileEntity examScheduleFileEntity);

    void deleteExamScheduleFile(Long departmentId);

    boolean isExamScheduleFileExist(String fileId);
}
