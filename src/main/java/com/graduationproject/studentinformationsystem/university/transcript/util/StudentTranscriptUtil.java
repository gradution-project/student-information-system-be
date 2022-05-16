package com.graduationproject.studentinformationsystem.university.transcript.util;

public class StudentTranscriptUtil {

    private StudentTranscriptUtil() {
    }

    public static Double getMeanOfNoteWith2NumberAfterDot(final Double meanOfNote) {
        if (meanOfNote != null) {
            return Math.floor(meanOfNote * 100) / 100;
        }
        return null;
    }
}
