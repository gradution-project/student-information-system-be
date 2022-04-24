package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.StudentGraduateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRequestConverter {

    public StudentGraduateRequest generateGraduateRequest(final Long studentId,
                                                          final SisOperationInfoRequest operationInfoRequest) {

        return StudentGraduateRequest.builder()
                .studentId(studentId)
                .operationInfoRequest(operationInfoRequest).build();
    }
}
