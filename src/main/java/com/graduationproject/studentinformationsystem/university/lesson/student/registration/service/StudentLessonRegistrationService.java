package com.graduationproject.studentinformationsystem.university.lesson.student.registration.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationApproveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationRejectRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationDetailResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;

import java.text.ParseException;
import java.util.List;

public interface StudentLessonRegistrationService {

    List<StudentLessonRegistrationResponse> getAllStudentLessonRegistrationsByStatus(StudentLessonRegistrationStatus status);

    StudentLessonRegistrationDetailResponse getStudentLessonRegistrationDetailByRegistrationId(String registrationId)
            throws SisNotExistException;

    String getStudentLessonRegistrationIdByStudentId(Long studentId)
            throws SisNotExistException;

    StudentLessonRegistrationDetailResponse saveStudentLessonRegistration(StudentLessonRegistrationSaveRequest saveRequest)
            throws SisAlreadyException, SisNotExistException;

    StudentLessonRegistrationDetailResponse approveStudentLessonRegistration(StudentLessonRegistrationApproveRequest approveRequest)
            throws SisAlreadyException, SisNotExistException, SisProcessException, ParseException;

    StudentLessonRegistrationDetailResponse rejectStudentLessonRegistration(StudentLessonRegistrationRejectRequest rejectRequest)
            throws SisAlreadyException, SisNotExistException;
}
