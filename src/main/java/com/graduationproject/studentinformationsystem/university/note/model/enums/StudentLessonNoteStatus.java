package com.graduationproject.studentinformationsystem.university.note.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentLessonNoteStatus {

    UNFINALISED("Sonuçlandırılmadı"),
    PASSED("Geçti"),
    FAILED("Kaldı"),
    FAILED_FROM_ABSENTEEISM("Devamsızlıktan Kaldı");

    private final String tr;
}
