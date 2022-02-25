package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentLessonResponse {

    private Long createdUserId;
    private String createdDate;

    private StudentInfoResponse studentInfoResponse;
    private LessonResponse lessonResponse;
}
