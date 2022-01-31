package com.graduationproject.studentinformationsystem.university.student.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentAcademicInfoConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.university.student.repository.StudentAcademicInfoRepository;
import com.graduationproject.studentinformationsystem.university.student.service.StudentAcademicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentAcademicInfoServiceImpl implements StudentAcademicInfoService {

    private final StudentAcademicInfoRepository academicInfoRepository;

    @Override
    public List<StudentAcademicInfoResponse> getAllStudentAcademicInfosByStatus(StudentStatus status) {
        return StudentAcademicInfoConverter.entitiesToResponses(academicInfoRepository.getAllStudentAcademicInfosByStatus(status));
    }

    @Override
    public StudentAcademicInfoResponse getStudentAcademicInfoById(Long studentId) {
        return getStudentAcademicInfoResponse(studentId);
    }

    @Override
    public void saveStudentAcademicInfo(Long studentId,
                                        String studentEmail,
                                        StudentAcademicInfoRequest academicInfoRequest,
                                        SisOperationInfoRequest operationInfoRequest) {

        StudentAcademicInfoEntity academicInfoEntity = StudentAcademicInfoConverter
                .generateSaveEntity(studentId, studentEmail, academicInfoRequest, operationInfoRequest);
        academicInfoRepository.saveStudentAcademicInfo(academicInfoEntity);
    }

    @Override
    public StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId,
                                                                 StudentAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        StudentAcademicInfoEntity entity = StudentAcademicInfoConverter.generateUpdateEntity(studentId, academicInfoUpdateRequest);
        academicInfoRepository.updateStudentAcademicInfo(entity);

        return getStudentAcademicInfoResponse(studentId);
    }

    @Override
    public void deleteStudentAcademicInfo(StudentDeleteRequest deleteRequest) {
        StudentAcademicInfoEntity entity = StudentAcademicInfoConverter.generateDeleteEntity(deleteRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(entity);
    }

    @Override
    public void passivateStudentAcademicInfo(StudentPassivateRequest passivateRequest) {
        StudentAcademicInfoEntity entity = StudentAcademicInfoConverter.generatePassiveEntity(passivateRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(entity);
    }

    @Override
    public void activateStudentAcademicInfo(StudentActivateRequest activateRequest) {
        StudentAcademicInfoEntity entity = StudentAcademicInfoConverter.generateActiveEntity(activateRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(entity);
    }

    @Override
    public void graduateStudentAcademicInfo(StudentGraduateRequest graduateRequest) {
        StudentAcademicInfoEntity entity = StudentAcademicInfoConverter.generateGraduateEntity(graduateRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(entity);
    }

    @Override
    public List<Long> getAllStudentIdsByDepartmentId(Long departmentId) {
        return academicInfoRepository.getAllStudentIdsByDepartmentId(departmentId);
    }

    @Override
    public boolean isStudentExist(Long studentId) {
        return academicInfoRepository.isStudentExist(studentId);
    }

    @Override
    public boolean isStudentDeleted(Long studentId) {
        return academicInfoRepository.isStudentDeleted(studentId);
    }

    @Override
    public boolean isStudentPassive(Long studentId) {
        return academicInfoRepository.isStudentPassive(studentId);
    }

    @Override
    public boolean isStudentActive(Long studentId) {
        return academicInfoRepository.isStudentActive(studentId);
    }

    @Override
    public boolean isStudentGraduated(Long studentId) {
        return academicInfoRepository.isStudentGraduated(studentId);
    }

    private StudentAcademicInfoResponse getStudentAcademicInfoResponse(Long studentId) {
        return StudentAcademicInfoConverter.entityToResponse(academicInfoRepository.getStudentAcademicInfoById(studentId));
    }
}
