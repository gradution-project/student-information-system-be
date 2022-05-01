package com.graduationproject.studentinformationsystem.university.note.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentLessonNoteState {

    NOT_ENTERED("Not Girişi Yapılmadı"),
    UNCONFIRMED("Kesinleştirilmedi"),
    CONFIRMED("Kesinleştirildi");

    private final String tr;
}
