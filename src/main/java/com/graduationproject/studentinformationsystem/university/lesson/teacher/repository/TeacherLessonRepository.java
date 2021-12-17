package com.graduationproject.studentinformationsystem.university.lesson.teacher.repository;

import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherDeleteLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherSaveLessonEntity;

import java.util.List;

public interface TeacherLessonRepository {

    List<TeacherLessonEntity> getAllTeachersLessons();

    List<TeacherLessonEntity> getTeacherLessonsByTeacherId(Long teacherId);

    TeacherLessonEntity getTeacherLessonByTeacherIdAndLessonId(Long teacherId, Long lessonId);

    void saveTeacherLesson(TeacherSaveLessonEntity saveLessonEntity);

    void deleteTeacherLesson(TeacherDeleteLessonEntity deleteLessonEntity);

    boolean isTeacherLessonExist(Long teacherId, Long lessonId);
}
