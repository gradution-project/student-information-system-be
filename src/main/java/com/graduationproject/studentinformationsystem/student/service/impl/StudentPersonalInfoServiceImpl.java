package com.graduationproject.studentinformationsystem.student.service.impl;

import com.graduationproject.studentinformationsystem.student.model.dto.converter.StudentPersonalInfoConverter;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.entity.StudentPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.student.repository.StudentPersonalInfoRepository;
import com.graduationproject.studentinformationsystem.student.service.StudentPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentPersonalInfoServiceImpl implements StudentPersonalInfoService {

    private final StudentPersonalInfoRepository personalInfoRepository;

    @Override
    public List<StudentPersonalInfoResponse> getAllStudentPersonalInfosByStatus(StudentStatus status) {
        return StudentPersonalInfoConverter.entityListToResponseList(personalInfoRepository.getAllStudentPersonalInfosByStatus(status));
    }

    @Override
    public StudentPersonalInfoResponse getStudentPersonalInfoById(Long studentId) {
        return getStudentPersonalInfoResponse(studentId);
    }

    @Override
    public void saveStudentPersonalInfo(Long studentId, StudentPersonalInfoRequest request) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateSaveEntity(studentId, request);
        personalInfoRepository.saveStudentPersonalInfo(entity);
    }

    @Override
    public StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId, StudentPersonalInfoRequest request) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateUpdateEntity(studentId, request);
        personalInfoRepository.updateStudentPersonalInfo(entity);

        return getStudentPersonalInfoResponse(studentId);
    }

    @Override
    public void deleteStudentPersonalInfo(Long studentId) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateDeleteEntity(studentId);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    @Override
    public void passivateStudentPersonalInfo(Long studentId) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generatePassiveEntity(studentId);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    @Override
    public void activateStudentPersonalInfo(Long studentId) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateActiveEntity(studentId);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    @Override
    public void graduateStudentPersonalInfo(Long studentId) {
        StudentPersonalInfoEntity entity = StudentPersonalInfoConverter.generateGraduateEntity(studentId);
        personalInfoRepository.updateStudentPersonalInfoStatus(entity);
    }

    private StudentPersonalInfoResponse getStudentPersonalInfoResponse(Long studentId) {
        return StudentPersonalInfoConverter.entityToResponse(personalInfoRepository.getStudentPersonalInfoById(studentId));
    }
}
