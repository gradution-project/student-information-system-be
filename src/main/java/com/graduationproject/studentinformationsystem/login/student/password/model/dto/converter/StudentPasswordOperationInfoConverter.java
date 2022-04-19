package com.graduationproject.studentinformationsystem.login.student.password.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.student.password.model.entity.StudentPasswordOperationEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StudentPasswordOperationInfoConverter {

    @Value("${sis.fe-url}")
    private String feUrl;

    public StudentPasswordOperationEntity generateEntity(final Long studentId) {

        return StudentPasswordOperationEntity.builder()
                .operationId(SisUtil.generateRandomUUID())
                .studentId(studentId)
                .expireDate(LocalDateTime.now().plusDays(1))
                .build();
    }

    public StudentPasswordOperationResponse entityToResponse(final StudentPasswordOperationEntity passwordOperationEntity) {

        final String operationId = passwordOperationEntity.getOperationId();

        return StudentPasswordOperationResponse.builder()
                .operationId(operationId)
                .studentId(passwordOperationEntity.getStudentId())
                .expireDate(passwordOperationEntity.getExpireDate())
                .passwordChangeUrl(feUrl + "/login/student/change-password/" + operationId)
                .build();
    }
}
