package com.graduationproject.studentinformationsystem.university.lesson.student.common.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response.StudentLessonsResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationDetailResponse;

public interface StudentLessonOutService {

    StudentLessonsResponse saveStudentLessons(StudentLessonRegistrationDetailResponse registrationDetailResponse)
            throws SisAlreadyException;
}
