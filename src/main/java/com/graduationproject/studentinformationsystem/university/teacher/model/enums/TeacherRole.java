package com.graduationproject.studentinformationsystem.university.teacher.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeacherRole {

    TEACHER("Öğretmen"),
    ADVISOR("Danışman"),
    HEAD_OF_DEPARTMENT("Bölüm Başkanı");

    private final String tr;
}
