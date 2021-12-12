package com.graduationproject.studentinformationsystem.university.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.mail.service.TeacherMailService;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherResponseConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.university.teacher.model.exception.TeacherException;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherPersonalInfoService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherService;
import com.graduationproject.studentinformationsystem.university.teacher.util.TeacherUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherAcademicInfoService academicInfoService;
    private final TeacherPersonalInfoService personalInfoService;
    private final TeacherMailService teacherMailService;

    @Override
    public List<TeacherResponse> getAllTeachersByStatus(TeacherStatus status) {
        List<TeacherAcademicInfoResponse> academicInfoResponses = academicInfoService.getAllTeacherAcademicInfosByStatus(status);
        List<TeacherPersonalInfoResponse> personalInfoResponses = personalInfoService.getAllTeacherPersonalInfosByStatus(status);
        return TeacherResponseConverter.infoResponsesListToResponseList(academicInfoResponses, personalInfoResponses);
    }

    @Override
    public TeacherInfoDetailResponse getTeacherDetailById(Long teacherId) throws SisNotExistException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        return getTeacherInfoResponse(teacherId);
    }

    @Override
    public TeacherInfoDetailResponse saveTeacher(TeacherInfoRequest studentInfoRequest) {
        checkBeforeSaving(studentInfoRequest);

        Long teacherId = generateTeacherId(studentInfoRequest.getAcademicInfoRequest().getDepartmentId());
        String studentEmail = generateTeacherEmail(studentInfoRequest);

        academicInfoService.saveTeacherAcademicInfo(teacherId, studentEmail, studentInfoRequest.getAcademicInfoRequest());
        personalInfoService.saveTeacherPersonalInfo(teacherId, studentInfoRequest.getPersonalInfoRequest());

        TeacherInfoDetailResponse teacherInfoDetailResponse = getTeacherInfoResponse(teacherId);
        teacherMailService.sendFirstPasswordEmail(teacherInfoDetailResponse);
        return teacherInfoDetailResponse;
    }

    @Override
    public TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId, TeacherAcademicInfoRequest academicInfoRequest)
            throws SisNotExistException {

        checkBeforeUpdatingAcademicInfo(teacherId, academicInfoRequest);
        return academicInfoService.updateTeacherAcademicInfo(teacherId, academicInfoRequest);
    }

    @Override
    public TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest personalInfoRequest)
            throws SisNotExistException {

        checkBeforeUpdatingPersonalInfo(teacherId);
        return personalInfoService.updateTeacherPersonalInfo(teacherId, personalInfoRequest);
    }

    @Override
    public TeacherResponse deleteTeacher(Long teacherId) throws SisNotExistException, SisAlreadyException {
        checkBeforeDeleting(teacherId);
        academicInfoService.deleteTeacherAcademicInfo(teacherId);
        personalInfoService.deleteTeacherPersonalInfo(teacherId);
        return getTeacherResponse(teacherId);
    }

    @Override
    public TeacherResponse passivateTeacher(Long teacherId) throws SisNotExistException, SisAlreadyException {
        checkBeforePassivating(teacherId);
        academicInfoService.passivateTeacherAcademicInfo(teacherId);
        personalInfoService.passivateTeacherPersonalInfo(teacherId);
        return getTeacherResponse(teacherId);
    }

    @Override
    public TeacherResponse activateTeacher(Long teacherId) throws SisNotExistException, SisAlreadyException {
        checkBeforeActivating(teacherId);
        academicInfoService.activateTeacherAcademicInfo(teacherId);
        personalInfoService.activateTeacherPersonalInfo(teacherId);
        return getTeacherResponse(teacherId);
    }


    private Long generateTeacherId(Long departmentId) {
        List<Long> teacherIds = academicInfoService.getAllTeacherIdsByDepartmentId(departmentId);
        return TeacherUtil.generateTeacherId(departmentId, teacherIds);
    }

    private String generateTeacherEmail(TeacherInfoRequest studentInfoRequest) {
        return TeacherUtil.generateTeacherEmail(
                studentInfoRequest.getPersonalInfoRequest().getName(),
                studentInfoRequest.getPersonalInfoRequest().getSurname());
    }

    private TeacherInfoDetailResponse getTeacherInfoResponse(Long teacherId) {
        TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    private TeacherResponse getTeacherResponse(Long teacherId) {
        TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(TeacherInfoRequest studentInfoRequest) {
        ifDepartmentIdIsNotExistThrowNotExistException(studentInfoRequest.getAcademicInfoRequest().getDepartmentId());
    }

    private void checkBeforeUpdatingAcademicInfo(Long teacherId, TeacherAcademicInfoRequest academicInfoRequest)
            throws SisNotExistException {

        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifDepartmentIdIsNotExistThrowNotExistException(academicInfoRequest.getDepartmentId());
    }

    private void checkBeforeUpdatingPersonalInfo(Long teacherId) throws SisNotExistException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
    }

    private void checkBeforeDeleting(Long teacherId) throws SisNotExistException, SisAlreadyException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifTeacherIsAlreadyDeletedThrowAlreadyException(teacherId);
    }

    private void checkBeforePassivating(Long teacherId) throws SisNotExistException, SisAlreadyException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifTeacherIsAlreadyPassiveThrowAlreadyException(teacherId);
        ifTeacherIsAlreadyDeletedThrowAlreadyException(teacherId);
    }

    private void checkBeforeActivating(Long teacherId) throws SisNotExistException, SisAlreadyException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifTeacherIsAlreadyActiveThrowAlreadyException(teacherId);
        ifTeacherIsAlreadyDeletedThrowAlreadyException(teacherId);
    }


    /**
     * Throw Exceptions
     */

    private void ifTeacherIsNotExistThrowNotExistException(Long teacherId) throws SisNotExistException {
        if (!academicInfoService.isTeacherExist(teacherId)) {
            TeacherException.throwNotExistException(teacherId);
        }
    }

    private void ifDepartmentIdIsNotExistThrowNotExistException(Long departmentId) {
        // TODO: ifDepartmentIdIsNotExistThrowNotExistException
//        if (!departmentService.isDepartmentExist(teacherId)) {
//            SisException.throwNotExistException();
//        }
    }

    private void ifTeacherIsAlreadyDeletedThrowAlreadyException(Long studentId) throws SisAlreadyException {
        if (academicInfoService.isTeacherDeleted(studentId)) {
            TeacherException.throwAlreadyDeletedException(studentId);
        }
    }

    private void ifTeacherIsAlreadyPassiveThrowAlreadyException(Long studentId) throws SisAlreadyException {
        if (academicInfoService.isTeacherPassive(studentId)) {
            TeacherException.throwAlreadyPassiveException(studentId);
        }
    }

    private void ifTeacherIsAlreadyActiveThrowAlreadyException(Long studentId) throws SisAlreadyException {
        if (academicInfoService.isTeacherActive(studentId)) {
            TeacherException.throwAlreadyActiveException(studentId);
        }
    }
}