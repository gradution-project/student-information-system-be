package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentLessonRegistrationEntity extends SisBaseEntity {

    private String registrationId;
    private Long studentId;
    private String lessonsIds;
    private StudentLessonRegistrationStatus status;
}
