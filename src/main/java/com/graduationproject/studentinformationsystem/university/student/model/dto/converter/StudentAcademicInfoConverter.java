package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.student.model.dto.request.StudentAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentAcademicInfoConverter {

    private StudentAcademicInfoConverter() {
    }

    public static StudentAcademicInfoResponse entityToResponse(StudentAcademicInfoEntity entity) {
        return new ModelMapper().map(entity, StudentAcademicInfoResponse.class);
    }

    public static List<StudentAcademicInfoResponse> entityListToResponseList(List<StudentAcademicInfoEntity> entityList) {
        List<StudentAcademicInfoResponse> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(entityToResponse(entity)));
        return dtoList;
    }

    public static StudentAcademicInfoEntity generateSaveEntity(Long studentId, String studentEmail, StudentAcademicInfoRequest request) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(request.getDepartmentId())
                .degree(request.getDegree())
                .classLevel(request.getClassLevel())
                .email(studentEmail)
                .status(StudentStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(1L)
                .createdDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateUpdateEntity(Long studentId, StudentAcademicInfoRequest request) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(request.getDepartmentId())
                .degree(request.getDegree())
                .classLevel(request.getClassLevel())
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateDeleteEntity(Long studentId) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.DELETED)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generatePassiveEntity(Long studentId) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.PASSIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateActiveEntity(Long studentId) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.ACTIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateGraduateEntity(Long studentId) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.GRADUATED)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }
}
