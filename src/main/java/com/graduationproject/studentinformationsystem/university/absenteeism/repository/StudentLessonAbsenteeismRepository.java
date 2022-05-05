package com.graduationproject.studentinformationsystem.university.absenteeism.repository;

import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismSaveEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismUpdateEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;

import java.util.List;

public interface StudentLessonAbsenteeismRepository {

    List<StudentLessonAbsenteeismEntity> getAllStudentLessonsAbsenteeismByStudentId(Long studentId, Integer week);

    List<StudentLessonAbsenteeismEntity> getAllStudentsLessonsAbsenteeismByLessonId(Long lessonId, Integer week);

    StudentLessonAbsenteeismEntity getStudentLessonAbsenteeismById(String id);

    void saveStudentLessonAbsenteeism(StudentLessonAbsenteeismSaveEntity saveEntity);

    void updateStudentLessonAbsenteeism(StudentLessonAbsenteeismUpdateEntity updateEntity);

    void updateStudentLessonAbsenteeismStatus(String id, StudentLessonAbsenteeismStatus status);

    boolean isStudentLessonAbsenteeismExist(String id);

    Long getStudentIdByAbsenteeismId(String id);

    Long getLessonIdByAbsenteeismId(String id);

    boolean isStudentLessonAbsenteeismExist(Long studentId);

    Integer calculateTotalTheoreticalHours(String id);

    Integer calculateTotalPracticeHours(String id);

    Integer getMaxTheoreticalHoursById(String id);

    Integer getMaxPracticeHoursById(String id);
}
