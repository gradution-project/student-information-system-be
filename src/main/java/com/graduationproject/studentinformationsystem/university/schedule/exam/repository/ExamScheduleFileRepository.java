package com.graduationproject.studentinformationsystem.university.schedule.exam.repository;

import com.graduationproject.studentinformationsystem.university.schedule.common.model.entity.ScheduleFileEntity;

import java.util.List;

public interface ExamScheduleFileRepository {

    List<ScheduleFileEntity> getExamScheduleFilesByFacultyId(Long facultyId);

    ScheduleFileEntity getExamScheduleFileById(String fileId);

    ScheduleFileEntity getExamScheduleFileByDepartmentId(Long departmentId);

    void saveExamScheduleFile(ScheduleFileEntity scheduleFileEntity);

    void deleteExamScheduleFile(Long departmentId);

    boolean isExamScheduleFileExist(String fileId);
}
