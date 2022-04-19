package com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.entity.TeacherPasswordOperationEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TeacherPasswordOperationInfoConverter {

    @Value("${sis.fe-url}")
    private String feUrl;

    public TeacherPasswordOperationEntity generateEntity(final Long teacherId) {

        return TeacherPasswordOperationEntity.builder()
                .operationId(SisUtil.generateRandomUUID())
                .teacherId(teacherId)
                .expireDate(LocalDateTime.now().plusDays(1))
                .build();
    }

    public TeacherPasswordOperationResponse entityToResponse(final TeacherPasswordOperationEntity passwordOperationEntity) {

        final String operationId = passwordOperationEntity.getOperationId();

        return TeacherPasswordOperationResponse.builder()
                .operationId(operationId)
                .teacherId(passwordOperationEntity.getTeacherId())
                .expireDate(passwordOperationEntity.getExpireDate())
                .passwordChangeUrl(feUrl + "/login/teacher/change-password/" + operationId)
                .build();
    }
}
