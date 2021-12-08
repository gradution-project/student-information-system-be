package com.graduationproject.studentinformationsystem.student.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.student.model.dto.converter.StudentInfoResponseConverter;
import com.graduationproject.studentinformationsystem.student.model.dto.converter.StudentResponseConverter;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentResponse;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.student.model.exception.StudentException;
import com.graduationproject.studentinformationsystem.student.service.StudentAcademicInfoService;
import com.graduationproject.studentinformationsystem.student.service.StudentPersonalInfoService;
import com.graduationproject.studentinformationsystem.student.service.StudentService;
import com.graduationproject.studentinformationsystem.student.util.StudentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentAcademicInfoService academicInfoService;
    private final StudentPersonalInfoService personalInfoService;

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
    public StudentInfoDetailResponse saveStudent(StudentInfoRequest studentInfoRequest) {
        checkBeforeSaving(studentInfoRequest);

        Long studentId = generateStudentId(studentInfoRequest.getAcademicInfoRequest().getDepartmentId());
        String studentEmail = generateStudentEmail(studentInfoRequest);

        academicInfoService.saveStudentAcademicInfo(studentId, studentEmail, studentInfoRequest.getAcademicInfoRequest());
        personalInfoService.saveStudentPersonalInfo(studentId, studentInfoRequest.getPersonalInfoRequest());
        return getStudentInfoResponse(studentId);
    }

    @Override
    public StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId, StudentAcademicInfoRequest academicInfoRequest)
            throws SisNotExistException {

        checkBeforeUpdatingAcademicInfo(studentId, academicInfoRequest);
        return academicInfoService.updateStudentAcademicInfo(studentId, academicInfoRequest);
    }

    @Override
    public StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId, StudentPersonalInfoRequest personalInfoRequest)
            throws SisNotExistException {

        checkBeforeUpdatingPersonalInfo(studentId);
        return personalInfoService.updateStudentPersonalInfo(studentId, personalInfoRequest);
    }

    @Override
    public StudentResponse deleteStudent(Long studentId) throws SisNotExistException {
        checkBeforeDeleting(studentId);
        academicInfoService.deleteStudentAcademicInfo(studentId);
        personalInfoService.deleteStudentPersonalInfo(studentId);
        return getStudentResponse(studentId);
    }

    @Override
    public StudentResponse passivateStudent(Long studentId) throws SisNotExistException {
        checkBeforePassivating(studentId);
        academicInfoService.passivateStudentAcademicInfo(studentId);
        personalInfoService.passivateStudentPersonalInfo(studentId);
        return getStudentResponse(studentId);
    }

    @Override
    public StudentResponse activateStudent(Long studentId) throws SisNotExistException {
        checkBeforeActivating(studentId);
        academicInfoService.activateStudentAcademicInfo(studentId);
        personalInfoService.activateStudentPersonalInfo(studentId);
        return getStudentResponse(studentId);
    }

    @Override
    public StudentResponse graduateStudent(Long studentId) throws SisNotExistException {
        checkBeforeGraduating(studentId);
        academicInfoService.graduateStudentAcademicInfo(studentId);
        personalInfoService.graduateStudentPersonalInfo(studentId);
        return getStudentResponse(studentId);
    }


    private Long generateStudentId(Long departmentId) {
        List<Long> studentIdList = academicInfoService.getAllStudentIdsByDepartmentId(departmentId);
        return StudentUtil.generateStudentId(departmentId, studentIdList);
    }

    private String generateStudentEmail(StudentInfoRequest studentInfoRequest) {
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

    private void checkBeforeSaving(StudentInfoRequest studentInfoRequest) {
    }

    private void checkBeforeUpdatingAcademicInfo(Long studentId, StudentAcademicInfoRequest academicInfoRequest)
            throws SisNotExistException {

        ifStudentIsNotExistThrowNotExistException(studentId);
        ifDepartmentIdIsNotExistThrowNotExistException(academicInfoRequest.getDepartmentId());
    }

    private void checkBeforeUpdatingPersonalInfo(Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void checkBeforeDeleting(Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void checkBeforePassivating(Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void checkBeforeActivating(Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void checkBeforeGraduating(Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
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
}
