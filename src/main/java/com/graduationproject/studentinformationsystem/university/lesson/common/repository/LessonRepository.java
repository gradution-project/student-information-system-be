package com.graduationproject.studentinformationsystem.university.lesson.common.repository;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;

import java.util.List;

public interface LessonRepository {

    List<LessonEntity> getAllLessonsByStatus(LessonStatus status);

    LessonEntity getLessonById(Long lessonId);

    void saveLesson(LessonEntity lessonEntity);

    void updateLesson(LessonEntity lessonEntity);

    void updateLessonStatus(LessonEntity lessonEntity);

    List<Long> getAllLessonIdsByDepartmentId(Long departmentId);

    boolean isLessonExist(Long lessonId);

    boolean isLessonDeleted(Long lessonId);

    boolean isLessonPassive(Long lessonId);

    boolean isLessonActive(Long lessonId);
}
