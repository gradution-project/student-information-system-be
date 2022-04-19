package com.graduationproject.studentinformationsystem.university.student.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.student.password.service.StudentPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentOutService;
import com.graduationproject.studentinformationsystem.university.mail.service.StudentMailService;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentResponseConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.university.student.model.exception.StudentException;
import com.graduationproject.studentinformationsystem.university.student.service.StudentAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentPersonalInfoService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentService;
import com.graduationproject.studentinformationsystem.university.student.util.StudentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final DepartmentOutService departmentOutService;
    private final StudentPasswordOperationOutService passwordOperationOutService;

    private final StudentAcademicInfoService academicInfoService;
    private final StudentPersonalInfoService personalInfoService;
    private final StudentMailService mailService;

    @Override
    public List<StudentInfoResponse> getAllStudentsByStatus(final StudentStatus status) {
        final List<StudentAcademicInfoResponse> academicInfoResponses = academicInfoService.getAllStudentAcademicInfosByStatus(status);
        final List<StudentPersonalInfoResponse> personalInfoResponses = personalInfoService.getAllStudentPersonalInfosByStatus(status);
        return StudentResponseConverter.infoResponsesListToResponseList(academicInfoResponses, personalInfoResponses);
    }

    @Override
    public StudentInfoDetailResponse getStudentDetailById(final Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        return getStudentInfoDetailResponse(studentId);
    }

    @Override
    public StudentInfoDetailResponse saveStudent(final StudentSaveRequest saveRequest) throws SisNotExistException {
        checkBeforeSaving(saveRequest);

        final Long studentId = generateStudentId(saveRequest.getAcademicInfoRequest().getDepartmentId());
        final String studentEmail = generateStudentEmail(saveRequest);
        final StudentAcademicInfoRequest academicInfoRequest = saveRequest.getAcademicInfoRequest();
        final StudentPersonalInfoRequest personalInfoRequest = saveRequest.getPersonalInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        academicInfoService.saveStudentAcademicInfo(studentId, studentEmail, academicInfoRequest, operationInfoRequest);
        personalInfoService.saveStudentPersonalInfo(studentId, personalInfoRequest, operationInfoRequest);

        final StudentInfoDetailResponse studentInfoDetailResponse = getStudentInfoDetailResponse(studentId);

        passwordOperationOutService.saveOrUpdatePasswordOperation(studentId, saveRequest.getOperationInfoRequest().getFeUrl());
        mailService.sendSavedEmail(studentInfoDetailResponse);
        return studentInfoDetailResponse;
    }

    @Override
    public StudentAcademicInfoResponse updateStudentAcademicInfo(final Long studentId,
                                                                 final StudentAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        checkBeforeUpdatingAcademicInfo(studentId, academicInfoUpdateRequest);
        return academicInfoService.updateStudentAcademicInfo(studentId, academicInfoUpdateRequest);
    }

    @Override
    public StudentPersonalInfoResponse updateStudentPersonalInfo(final Long studentId,
                                                                 final StudentPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException {

        checkBeforeUpdatingPersonalInfo(studentId);
        return personalInfoService.updateStudentPersonalInfo(studentId, personalInfoUpdateRequest);
    }

    @Override
    public StudentInfoResponse deleteStudent(final StudentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeDeleting(deleteRequest.getStudentId());
        academicInfoService.deleteStudentAcademicInfo(deleteRequest);
        personalInfoService.deleteStudentPersonalInfo(deleteRequest);
        return getStudentInfoResponse(deleteRequest.getStudentId());
    }

    @Override
    public StudentInfoResponse passivateStudent(final StudentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforePassivating(passivateRequest.getStudentId());
        academicInfoService.passivateStudentAcademicInfo(passivateRequest);
        personalInfoService.passivateStudentPersonalInfo(passivateRequest);
        return getStudentInfoResponse(passivateRequest.getStudentId());
    }

    @Override
    public StudentInfoResponse activateStudent(final StudentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeActivating(activateRequest.getStudentId());
        academicInfoService.activateStudentAcademicInfo(activateRequest);
        personalInfoService.activateStudentPersonalInfo(activateRequest);
        return getStudentInfoResponse(activateRequest.getStudentId());
    }

    @Override
    public StudentInfoResponse graduateStudent(final StudentGraduateRequest graduateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeGraduating(graduateRequest.getStudentId());
        academicInfoService.graduateStudentAcademicInfo(graduateRequest);
        personalInfoService.graduateStudentPersonalInfo(graduateRequest);
        return getStudentInfoResponse(graduateRequest.getStudentId());
    }


    private Long generateStudentId(final Long departmentId) {
        final List<Long> studentIds = academicInfoService.getAllStudentIdsByDepartmentId(departmentId);
        return StudentUtil.generateStudentId(departmentId, studentIds);
    }

    private String generateStudentEmail(final StudentSaveRequest studentInfoRequest) {
        final String name = studentInfoRequest.getPersonalInfoRequest().getName();
        final String surname = studentInfoRequest.getPersonalInfoRequest().getSurname();
        return StudentUtil.generateStudentEmail(name, surname);
    }

    private StudentInfoDetailResponse getStudentInfoDetailResponse(final Long studentId) {
        final StudentAcademicInfoResponse academicInfoResponse = academicInfoService.getStudentAcademicInfoById(studentId);
        final StudentPersonalInfoResponse personalInfoResponse = personalInfoService.getStudentPersonalInfoById(studentId);
        return StudentInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    private StudentInfoResponse getStudentInfoResponse(final Long studentId) {
        final StudentAcademicInfoResponse academicInfoResponse = academicInfoService.getStudentAcademicInfoById(studentId);
        final StudentPersonalInfoResponse personalInfoResponse = personalInfoService.getStudentPersonalInfoById(studentId);
        return StudentResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final StudentSaveRequest studentInfoRequest) throws SisNotExistException {
        final Long departmentId = studentInfoRequest.getAcademicInfoRequest().getDepartmentId();

        ifDepartmentIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeUpdatingAcademicInfo(final Long studentId, final StudentAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        final Long departmentId = academicInfoUpdateRequest.getAcademicInfoRequest().getDepartmentId();

        ifStudentIsNotExistThrowNotExistException(studentId);
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeUpdatingPersonalInfo(final Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void checkBeforeDeleting(final Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
    }

    private void checkBeforePassivating(final Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyPassiveThrowAlreadyException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
    }

    private void checkBeforeActivating(final Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyActiveThrowAlreadyException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
    }

    private void checkBeforeGraduating(final Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
        ifStudentIsAlreadyPassiveThrowAlreadyException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
    }


    /**
     * Throw Exceptions
     */

    private void ifDepartmentIsNotExistThrowNotExistException(final Long departmentId) throws SisNotExistException {
        departmentOutService.ifDepartmentIsNotExistThrowNotExistException(departmentId);
    }

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        if (!academicInfoService.isStudentExist(studentId)) {
            StudentException.throwNotExistException(studentId);
        }
    }

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

    private void ifStudentIsAlreadyActiveThrowAlreadyException(final Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentActive(studentId)) {
            StudentException.throwAlreadyActiveException(studentId);
        }
    }

    private void ifStudentIsAlreadyGraduatedThrowAlreadyException(final Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentGraduated(studentId)) {
            StudentException.throwAlreadyGraduatedException(studentId);
        }
    }
}
