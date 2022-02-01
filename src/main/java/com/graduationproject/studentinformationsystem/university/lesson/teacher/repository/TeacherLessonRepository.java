package com.graduationproject.studentinformationsystem.university.lesson.teacher.repository;

import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonDeleteEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonSaveEntity;

import java.util.List;

public interface TeacherLessonRepository {

    List<TeacherLessonEntity> getAllTeachersLessons();

    List<TeacherLessonEntity> getTeacherLessonsByTeacherId(Long teacherId);

    TeacherLessonEntity getTeacherLessonByTeacherIdAndLessonId(Long teacherId, Long lessonId);

    void saveTeacherLesson(TeacherLessonSaveEntity saveLessonEntity);

    void deleteTeacherLesson(TeacherLessonDeleteEntity deleteLessonEntity);

    boolean isTeacherLessonExist(Long teacherId, Long lessonId);
}
