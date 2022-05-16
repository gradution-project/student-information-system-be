package com.graduationproject.studentinformationsystem.university.transcript.model.dto.response;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonSemester;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class StudentTranscriptResponse {

    private Double meanOfAllNotes;
    private Map<LessonSemester, StudentTranscriptLessonNoteSemesterResponse> studentLessonsSemestersNotesResponse;
    private String fileDownloadUrl;
    private StudentInfoResponse studentInfoResponse;
}
