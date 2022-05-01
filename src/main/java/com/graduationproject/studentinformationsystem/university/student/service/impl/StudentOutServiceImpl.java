package com.graduationproject.studentinformationsystem.university.student.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentRequestConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentResponseConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.StudentGraduateRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.exception.StudentException;
import com.graduationproject.studentinformationsystem.university.student.service.StudentAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentOutServiceImpl implements StudentOutService {

    private final StudentAcademicInfoService academicInfoService;
    private final StudentPersonalInfoService personalInfoService;
    private final StudentRequestConverter requestConverter;

    @Override
    public StudentInfoDetailResponse getStudentInfoDetailResponse(final Long studentId) {
        final StudentAcademicInfoResponse academicInfoResponse = academicInfoService.getStudentAcademicInfoById(studentId);
        final StudentPersonalInfoResponse personalInfoResponse = personalInfoService.getStudentPersonalInfoById(studentId);
        return StudentInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    @Override
    public StudentInfoResponse getStudentInfoResponse(final Long studentId) {
        final StudentAcademicInfoResponse academicInfoResponse = academicInfoService.getStudentAcademicInfoById(studentId);
        final StudentPersonalInfoResponse personalInfoResponse = personalInfoService.getStudentPersonalInfoById(studentId);
        return StudentResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }

    @Override
    public StudentInfoResponse graduateStudent(final Long studentId, final SisOperationInfoRequest operationInfoRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeGraduating(studentId);

        final StudentGraduateRequest graduateRequest = requestConverter.generateGraduateRequest(studentId, operationInfoRequest);
        academicInfoService.graduateStudentAcademicInfo(graduateRequest);
        personalInfoService.graduateStudentPersonalInfo(graduateRequest);
        return getStudentInfoResponse(studentId);
    }

    @Override
    public void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        if (!academicInfoService.isStudentExist(studentId)) {
            StudentException.throwNotExistException(studentId);
        }
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeGraduating(final Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
        ifStudentIsAlreadyPassiveThrowAlreadyException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentIsAlreadyDeletedThrowAlreadyException(final Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentDeleted(studentId)) {
            StudentException.throwAlreadyDeletedException(studentId);
        }
    }

    private void ifStudentIsAlreadyPassiveThrowAlreadyException(final Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentPassive(studentId)) {
            StudentException.throwAlreadyPassiveException(studentId);
        }
    }

    private void ifStudentIsAlreadyGraduatedThrowAlreadyException(final Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentGraduated(studentId)) {
            StudentException.throwAlreadyGraduatedException(studentId);
        }
    }
}
