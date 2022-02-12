package com.graduationproject.studentinformationsystem.university.lesson.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LessonSemester {

    FIRST("1. Dönem"),
    SECOND("2. Dönem"),
    THIRD("3. Dönem"),
    FOURTH("4. Dönem"),
    FIFTH("5. Dönem"),
    SIXTH("6. Dönem"),
    SEVENTH("7. Dönem"),
    EIGHTH("8. Dönem"),
    NINTH("9. Dönem"),
    TENTH("10. Dönem"),
    ELEVENTH("11. Dönem"),
    TWELFTH("12. Dönem");

    private final String tr;
}
