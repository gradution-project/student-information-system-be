package com.graduationproject.studentinformationsystem.university.transcript.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentTranscriptLessonNoteSemesterResponse {

    private Double meanOfAllNotes;
    private List<StudentTranscriptLessonNoteResponse> studentTranscriptLessonNoteResponse;
}
