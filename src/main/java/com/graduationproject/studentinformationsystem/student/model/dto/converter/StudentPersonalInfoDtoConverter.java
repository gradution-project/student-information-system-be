package com.graduationproject.studentinformationsystem.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentPersonalInfoRequestDto;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentPersonalInfoResponseDto;
import com.graduationproject.studentinformationsystem.student.model.entity.StudentPersonalInfoEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class StudentPersonalInfoDtoConverter {

    public static StudentPersonalInfoEntity saveRequestDtoToEntity(Long studentId, StudentPersonalInfoRequestDto requestDto) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .tcNo(requestDto.getTcNo())
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .profilePhoto(requestDto.getProfilePhoto())
                .profilePhotoUrl(requestDto.getProfilePhotoUrl())
                .birthday(requestDto.getBirthday())
                .address(requestDto.getAddress())
                .createdUserId(1L)
                .createdDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity updateRequestDtoToEntity(Long studentId, StudentPersonalInfoRequestDto requestDto) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .tcNo(requestDto.getTcNo())
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .email(requestDto.getEmail())
                .phoneNumber(requestDto.getPhoneNumber())
                .profilePhoto(requestDto.getProfilePhoto())
                .profilePhotoUrl(requestDto.getProfilePhotoUrl())
                .birthday(requestDto.getBirthday())
                .address(requestDto.getAddress())
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    private static StudentPersonalInfoResponseDto entityToResponseDto(StudentPersonalInfoEntity entity) {
        return new ModelMapper().map(entity, StudentPersonalInfoResponseDto.class);
    }

    private static List<StudentPersonalInfoResponseDto> entityListToResponseDtoList(List<StudentPersonalInfoEntity> entityList) {
        List<StudentPersonalInfoResponseDto> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(entityToResponseDto(entity)));
        return dtoList;
    }
}
