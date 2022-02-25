package com.graduationproject.studentinformationsystem.university.lesson.student.common.repository;

import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonSaveEntity;

import java.util.List;

public interface StudentLessonRepository {

    List<StudentLessonEntity> getStudentLessonsByStudentId(Long studentId);

    void saveStudentLesson(StudentLessonSaveEntity saveLessonEntity);

    void deleteStudentLessons(Long studentId);

    boolean isStudentLessonsExist(Long studentId);

    boolean isStudentLessonExist(Long studentId, Long lessonId);
}
