package com.graduationproject.studentinformationsystem.university.lesson.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LessonCompulsoryOrElective {

    COMPULSORY("Zorunlu"),
    ELECTIVE("Seçmeli");

    private final String tr;
}
