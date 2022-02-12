package com.graduationproject.studentinformationsystem.university.schedule.lesson.repository;

import com.graduationproject.studentinformationsystem.university.schedule.common.model.entity.ScheduleFileEntity;

import java.util.List;

public interface LessonScheduleFileRepository {

    List<ScheduleFileEntity> getLessonScheduleFilesByFacultyId(Long facultyId);

    ScheduleFileEntity getLessonScheduleFileById(String fileId);

    ScheduleFileEntity getLessonScheduleFileByDepartmentId(Long departmentId);

    void saveLessonScheduleFile(ScheduleFileEntity scheduleFileEntity);

    void deleteLessonScheduleFile(Long departmentId);

    boolean isLessonScheduleFileExist(String fileId);
}
