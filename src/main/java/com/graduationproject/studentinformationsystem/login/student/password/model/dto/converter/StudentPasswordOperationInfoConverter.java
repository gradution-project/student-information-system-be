package com.graduationproject.studentinformationsystem.login.student.password.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.student.password.model.entity.StudentPasswordOperationEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StudentPasswordOperationInfoConverter {

    public StudentPasswordOperationEntity generateEntity(final Long studentId, final String feUrl) {

        return StudentPasswordOperationEntity.builder()
                .operationId(SisUtil.generateRandomUUID())
                .studentId(studentId)
                .expireDate(LocalDateTime.now().plusDays(1))
                .feUrl(feUrl)
                .build();
    }

    public StudentPasswordOperationResponse entityToResponse(final StudentPasswordOperationEntity passwordOperationEntity) {

        final String operationId = passwordOperationEntity.getOperationId();

        return StudentPasswordOperationResponse.builder()
                .operationId(operationId)
                .studentId(passwordOperationEntity.getStudentId())
                .expireDate(passwordOperationEntity.getExpireDate())
                .passwordChangeUrl(passwordOperationEntity.getFeUrl() + "/login/student/change-password/" + operationId)
                .build();
    }
}
