package com.graduationproject.studentinformationsystem.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentAcademicInfoRequestDto;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentAcademicInfoResponseDto;
import com.graduationproject.studentinformationsystem.student.model.entity.StudentAcademicInfoEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class StudentAcademicInfoDtoConverter {

    public static StudentAcademicInfoEntity saveRequestDtoToEntity(Long studentId, StudentAcademicInfoRequestDto requestDto) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(requestDto.getDepartmentId())
                .degree(requestDto.getDegree())
                .classLevel(requestDto.getClassLevel())
                .email(requestDto.getEmail())
                .registrationDate(new Date())
                .createdUserId(1L)
                .createdDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity updateRequestDtoToEntity(Long studentId, StudentAcademicInfoRequestDto requestDto) {
        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(requestDto.getDepartmentId())
                .degree(requestDto.getDegree())
                .classLevel(requestDto.getClassLevel())
                .email(requestDto.getEmail())
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    private static StudentAcademicInfoResponseDto entityToResponseDto(StudentAcademicInfoEntity entity) {
        return new ModelMapper().map(entity, StudentAcademicInfoResponseDto.class);
    }

    private static List<StudentAcademicInfoResponseDto> entityListToResponseDtoList(List<StudentAcademicInfoEntity> entityList) {
        List<StudentAcademicInfoResponseDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(entityToResponseDto(entity)));
        return dtoList;
    }
}
