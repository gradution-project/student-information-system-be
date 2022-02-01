package com.graduationproject.studentinformationsystem.university.student.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.repository.DepartmentRepository;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
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

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;

    private final StudentAcademicInfoRepository academicInfoRepository;

    @Override
    public List<StudentAcademicInfoResponse> getAllStudentAcademicInfosByStatus(StudentStatus status) {
        final List<StudentAcademicInfoEntity> academicInfoEntities = academicInfoRepository.getAllStudentAcademicInfosByStatus(status);
        setDepartmentEntities(academicInfoEntities);
        return StudentAcademicInfoConverter.entitiesToResponses(academicInfoEntities);
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

        StudentAcademicInfoEntity academicInfoEntity = StudentAcademicInfoConverter
                .generateUpdateEntity(studentId, academicInfoUpdateRequest);
        academicInfoRepository.updateStudentAcademicInfo(academicInfoEntity);

        return getStudentAcademicInfoResponse(studentId);
    }

    @Override
    public void deleteStudentAcademicInfo(StudentDeleteRequest deleteRequest) {
        StudentAcademicInfoEntity academicInfoEntity = StudentAcademicInfoConverter.generateDeleteEntity(deleteRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public void passivateStudentAcademicInfo(StudentPassivateRequest passivateRequest) {
        StudentAcademicInfoEntity academicInfoEntity = StudentAcademicInfoConverter.generatePassiveEntity(passivateRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public void activateStudentAcademicInfo(StudentActivateRequest activateRequest) {
        StudentAcademicInfoEntity academicInfoEntity = StudentAcademicInfoConverter.generateActiveEntity(activateRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public void graduateStudentAcademicInfo(StudentGraduateRequest graduateRequest) {
        StudentAcademicInfoEntity academicInfoEntity = StudentAcademicInfoConverter.generateGraduateEntity(graduateRequest);
        academicInfoRepository.updateStudentAcademicInfoStatus(academicInfoEntity);
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
        final StudentAcademicInfoEntity academicInfoEntity = academicInfoRepository.getStudentAcademicInfoById(studentId);
        setDepartmentEntity(academicInfoEntity);
        return StudentAcademicInfoConverter.entityToResponse(academicInfoEntity);
    }

    private void setDepartmentEntity(final StudentAcademicInfoEntity academicInfoEntity) {
        final Long departmentId = academicInfoEntity.getDepartmentId();
        final DepartmentEntity departmentEntity = departmentRepository.getDepartmentById(departmentId);
        setFacultyEntity(departmentEntity);
        academicInfoEntity.setDepartmentEntity(departmentEntity);
    }

    private void setDepartmentEntities(final List<StudentAcademicInfoEntity> academicInfoEntities) {
        academicInfoEntities.forEach(this::setDepartmentEntity);
    }

    private void setFacultyEntity(final DepartmentEntity departmentEntity) {
        final Long facultyId = departmentEntity.getFacultyId();
        final FacultyEntity facultyEntity = facultyRepository.getFacultyById(facultyId);
        departmentEntity.setFacultyEntity(facultyEntity);
    }
}
