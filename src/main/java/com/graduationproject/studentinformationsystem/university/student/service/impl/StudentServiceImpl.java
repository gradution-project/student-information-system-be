package com.graduationproject.studentinformationsystem.university.student.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.mail.service.StudentMailService;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentResponseConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentResponse;
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

    private final StudentAcademicInfoService academicInfoService;
    private final StudentPersonalInfoService personalInfoService;
    private final StudentMailService mailService;

    @Override
    public List<StudentResponse> getAllStudentsByStatus(StudentStatus status) {
        List<StudentAcademicInfoResponse> academicInfoResponseList = academicInfoService.getAllStudentAcademicInfosByStatus(status);
        List<StudentPersonalInfoResponse> personalInfoResponseList = personalInfoService.getAllStudentPersonalInfosByStatus(status);
        return StudentResponseConverter.infoResponsesListToResponseList(academicInfoResponseList, personalInfoResponseList);
    }

    @Override
    public StudentInfoDetailResponse getStudentDetailById(Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        return getStudentInfoResponse(studentId);
    }

    @Override
    public StudentInfoDetailResponse saveStudent(StudentSaveInfoRequest saveInfoRequest) {
        checkBeforeSaving(saveInfoRequest);

        Long studentId = generateStudentId(saveInfoRequest.getAcademicInfoRequest().getDepartmentId());
        String studentEmail = generateStudentEmail(saveInfoRequest);

        academicInfoService.saveStudentAcademicInfo(studentId, studentEmail,
                saveInfoRequest.getAcademicInfoRequest(),
                saveInfoRequest.getOperationInfoRequest());
        personalInfoService.saveStudentPersonalInfo(studentId,
                saveInfoRequest.getPersonalInfoRequest(),
                saveInfoRequest.getOperationInfoRequest());

        StudentInfoDetailResponse studentInfoDetailResponse = getStudentInfoResponse(studentId);
        mailService.sendFirstPasswordEmail(studentInfoDetailResponse);
        return studentInfoDetailResponse;
    }

    @Override
    public StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId,
                                                                 StudentUpdateAcademicInfoRequest updateAcademicInfoRequest)
            throws SisNotExistException {

        checkBeforeUpdatingAcademicInfo(studentId, updateAcademicInfoRequest);
        return academicInfoService.updateStudentAcademicInfo(studentId, updateAcademicInfoRequest);
    }

    @Override
    public StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId,
                                                                 StudentUpdatePersonalInfoRequest updatePersonalInfoRequest)
            throws SisNotExistException {

        checkBeforeUpdatingPersonalInfo(studentId);
        return personalInfoService.updateStudentPersonalInfo(studentId, updatePersonalInfoRequest);
    }

    @Override
    public StudentResponse deleteStudent(StudentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeDeleting(deleteRequest.getStudentId());
        academicInfoService.deleteStudentAcademicInfo(deleteRequest);
        personalInfoService.deleteStudentPersonalInfo(deleteRequest);
        return getStudentResponse(deleteRequest.getStudentId());
    }

    @Override
    public StudentResponse passivateStudent(StudentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforePassivating(passivateRequest.getStudentId());
        academicInfoService.passivateStudentAcademicInfo(passivateRequest);
        personalInfoService.passivateStudentPersonalInfo(passivateRequest);
        return getStudentResponse(passivateRequest.getStudentId());
    }

    @Override
    public StudentResponse activateStudent(StudentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeActivating(activateRequest.getStudentId());
        academicInfoService.activateStudentAcademicInfo(activateRequest);
        personalInfoService.activateStudentPersonalInfo(activateRequest);
        return getStudentResponse(activateRequest.getStudentId());
    }

    @Override
    public StudentResponse graduateStudent(StudentGraduateRequest graduateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeGraduating(graduateRequest.getStudentId());
        academicInfoService.graduateStudentAcademicInfo(graduateRequest);
        personalInfoService.graduateStudentPersonalInfo(graduateRequest);
        return getStudentResponse(graduateRequest.getStudentId());
    }


    private Long generateStudentId(Long departmentId) {
        List<Long> studentIds = academicInfoService.getAllStudentIdsByDepartmentId(departmentId);
        return StudentUtil.generateStudentId(departmentId, studentIds);
    }

    private String generateStudentEmail(StudentSaveInfoRequest studentInfoRequest) {
        return StudentUtil.generateStudentEmail(
                studentInfoRequest.getPersonalInfoRequest().getName(),
                studentInfoRequest.getPersonalInfoRequest().getSurname());
    }

    private StudentInfoDetailResponse getStudentInfoResponse(Long studentId) {
        StudentAcademicInfoResponse academicInfoResponse = academicInfoService.getStudentAcademicInfoById(studentId);
        StudentPersonalInfoResponse personalInfoResponse = personalInfoService.getStudentPersonalInfoById(studentId);
        return StudentInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    private StudentResponse getStudentResponse(Long studentId) {
        StudentAcademicInfoResponse academicInfoResponse = academicInfoService.getStudentAcademicInfoById(studentId);
        StudentPersonalInfoResponse personalInfoResponse = personalInfoService.getStudentPersonalInfoById(studentId);
        return StudentResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(StudentSaveInfoRequest studentInfoRequest) {
        ifDepartmentIdIsNotExistThrowNotExistException(studentInfoRequest.getAcademicInfoRequest().getDepartmentId());
    }

    private void checkBeforeUpdatingAcademicInfo(Long studentId, StudentUpdateAcademicInfoRequest updateAcademicInfoRequest)
            throws SisNotExistException {

        ifStudentIsNotExistThrowNotExistException(studentId);
        ifDepartmentIdIsNotExistThrowNotExistException(updateAcademicInfoRequest.getAcademicInfoRequest().getDepartmentId());
    }

    private void checkBeforeUpdatingPersonalInfo(Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void checkBeforeDeleting(Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
    }

    private void checkBeforePassivating(Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyPassiveThrowAlreadyException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
    }

    private void checkBeforeActivating(Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyActiveThrowAlreadyException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
    }

    private void checkBeforeGraduating(Long studentId) throws SisNotExistException, SisAlreadyException {
        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentIsAlreadyGraduatedThrowAlreadyException(studentId);
        ifStudentIsAlreadyPassiveThrowAlreadyException(studentId);
        ifStudentIsAlreadyDeletedThrowAlreadyException(studentId);
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentIsNotExistThrowNotExistException(Long studentId) throws SisNotExistException {
        if (!academicInfoService.isStudentExist(studentId)) {
            StudentException.throwNotExistException(studentId);
        }
    }

    private void ifDepartmentIdIsNotExistThrowNotExistException(Long departmentId) {
        // TODO: ifDepartmentIdIsNotExistThrowNotExistException
//        if (!departmentService.isDepartmentExist(studentId)) {
//            SisException.throwNotExistException();
//        }
    }

    private void ifStudentIsAlreadyDeletedThrowAlreadyException(Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentDeleted(studentId)) {
            StudentException.throwAlreadyDeletedException(studentId);
        }
    }

    private void ifStudentIsAlreadyPassiveThrowAlreadyException(Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentPassive(studentId)) {
            StudentException.throwAlreadyPassiveException(studentId);
        }
    }

    private void ifStudentIsAlreadyActiveThrowAlreadyException(Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentActive(studentId)) {
            StudentException.throwAlreadyActiveException(studentId);
        }
    }

    private void ifStudentIsAlreadyGraduatedThrowAlreadyException(Long studentId) throws SisAlreadyException {
        if (academicInfoService.isStudentGraduated(studentId)) {
            StudentException.throwAlreadyGraduatedException(studentId);
        }
    }
}
