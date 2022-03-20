package com.graduationproject.studentinformationsystem.university.note.util;

public class StudentLessonNoteUtil {

    private StudentLessonNoteUtil() {
    }

    public static Double getMeanOfNoteWith2NumberAfterDot(final Double meanOfNote) {
        if (meanOfNote != null) {
            return Math.floor(meanOfNote * 100) / 100;
        }
        return null;
    }
}
