package com.graduationproject.studentinformationsystem.university.lesson.student.registration.repository;

import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.entity.StudentLessonRegistrationEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;

import java.util.List;

public interface StudentLessonRegistrationRepository {

    List<StudentLessonRegistrationEntity> getAllStudentLessonRegistrationsByStatus(StudentLessonRegistrationStatus status);

    StudentLessonRegistrationEntity getStudentLessonRegistrationByRegistrationId(String registrationId);

    void saveStudentLessonRegistration(StudentLessonRegistrationEntity registrationEntity);

    void updateStudentLessonRegistration(StudentLessonRegistrationEntity registrationEntity);

    void updateStudentLessonRegistrationStatus(StudentLessonRegistrationEntity registrationEntity);

    boolean isStudentLessonRegistrationExist(String registrationId);

    boolean isStudentLessonRegistrationWaiting(String registrationId);

    boolean isStudentLessonRegistrationApproved(String registrationId);

    boolean isStudentLessonRegistrationRejected(String registrationId);

    String getRegistrationId(Long studentId);
}
