package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class StudentLessonRegistrationDetailResponse extends SisBaseResponse {

    private String registrationId;
    private StudentLessonRegistrationStatus status;

    private StudentInfoResponse studentInfoResponse;
    private List<LessonResponse> lessonResponses;
}
