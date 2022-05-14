package com.graduationproject.studentinformationsystem.university.transcript.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonSemester;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;
import com.graduationproject.studentinformationsystem.university.transcript.controller.endpoint.StudentTranscriptControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.transcript.model.dto.response.StudentTranscriptLessonNoteResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
@RequiredArgsConstructor
public class StudentTranscriptInfoConverter {

    @Value("${sis.be-url}")
    private String beUrl;

    public Map<LessonSemester, List<StudentTranscriptLessonNoteResponse>> generateStudentLessonsSemestersNotesResponseMap(
            final List<StudentLessonNoteResponse> studentLessonNoteResponses) {

        final Map<LessonSemester, List<StudentTranscriptLessonNoteResponse>> studentLessonsSemestersNotesResponse = new TreeMap<>();
        studentLessonNoteResponses.forEach(studentLessonNoteResponse -> {
            final LessonResponse lessonResponse = studentLessonNoteResponse.getLessonResponse();
            final LessonSemester lessonSemester = lessonResponse.getSemester();

            final StudentTranscriptLessonNoteResponse studentLessonNoteTranscriptResponse = StudentTranscriptLessonNoteResponse.builder()
                    .lessonId(lessonResponse.getLessonId())
                    .name(lessonResponse.getName())
                    .credit(lessonResponse.getCredit())
                    .compulsoryOrElective(lessonResponse.getCompulsoryOrElective())
                    .meanOfNote(studentLessonNoteResponse.getMeanOfNote())
                    .status(studentLessonNoteResponse.getStatus()).build();

            final List<StudentTranscriptLessonNoteResponse> tempStudentLessonNoteTranscriptResponses = studentLessonsSemestersNotesResponse.get(lessonSemester);

            if (!CollectionUtils.isEmpty(tempStudentLessonNoteTranscriptResponses)) {
                tempStudentLessonNoteTranscriptResponses.add(studentLessonNoteTranscriptResponse);
                studentLessonsSemestersNotesResponse.put(lessonSemester, tempStudentLessonNoteTranscriptResponses);
            } else {
                final List<StudentTranscriptLessonNoteResponse> studentLessonNoteTranscriptResponses = new ArrayList<>();
                studentLessonNoteTranscriptResponses.add(studentLessonNoteTranscriptResponse);
                studentLessonsSemestersNotesResponse.put(lessonSemester, studentLessonNoteTranscriptResponses);
            }
        });

        return studentLessonsSemestersNotesResponse;
    }

    public String createFileDownloadUrl(final Long studentId) {
        final String basePath = StudentTranscriptControllerEndpoint.DOWNLOAD_BY_STUDENT_ID;
        return beUrl + basePath.replace("{studentId}", studentId.toString());
    }
}
