package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentLessonRegistrationStatus {

    WAITING("Onay Bekleniyor"),
    APPROVED("Onaylandı"),
    REJECTED("Reddedildi"),
    ALL("Hepsi");

    private final String tr;
}
