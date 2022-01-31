package com.graduationproject.studentinformationsystem.university.student.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.converter.StudentPersonalInfoConverter;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.university.student.repository.StudentPersonalInfoRepository;
import com.graduationproject.studentinformationsystem.university.student.service.StudentPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentPersonalInfoServiceImpl implements StudentPersonalInfoService {

    private final StudentPersonalInfoRepository personalInfoRepository;

    @Override
    public List<StudentPersonalInfoResponse> getAllStudentPersonalInfosByStatus(StudentStatus status) {
        return StudentPersonalInfoConverter.entitiesToResponses(personalInfoRepository.getAllStudentPersonalInfosByStatus(status));
    }

    @Override
    public StudentPersonalInfoResponse getStudentPersonalInfoById(Long studentId) {
        return getStudentPersonalInfoResponse(studentId);
    }

    @Override
    public void saveStudentPersonalInfo(Long studentId,
                                        StudentPersonalInfoRequest personalInfoRequest,
                                        SisOperationInfoRequest operationInfoRequest) {

        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter
                .generateSaveEntity(studentId, personalInfoRequest, operationInfoRequest);
        personalInfoRepository.saveStudentPersonalInfo(entity);
    }

    @Override
    public StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId,
                                                                 StudentPersonalInfoUpdateRequest personalInfoUpdateRequest) {

        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateUpdateEntity(studentId, personalInfoUpdateRequest);
        personalInfoRepository.updateStudentPersonalInfo(entity);

        return getStudentPersonalInfoResponse(studentId);
    }

    @Override
    public void deleteStudentPersonalInfo(StudentDeleteRequest deleteRequest) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateDeleteEntity(deleteRequest);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    @Override
    public void passivateStudentPersonalInfo(StudentPassivateRequest passivateRequest) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generatePassiveEntity(passivateRequest);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    @Override
    public void activateStudentPersonalInfo(StudentActivateRequest activateRequest) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateActiveEntity(activateRequest);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    @Override
    public void graduateStudentPersonalInfo(StudentGraduateRequest graduateRequest) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateGraduateEntity(graduateRequest);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    private StudentPersonalInfoResponse getStudentPersonalInfoResponse(Long studentId) {
        return StudentPersonalInfoConverter.entityToResponse(personalInfoRepository.getStudentPersonalInfoById(studentId));
    }
}
