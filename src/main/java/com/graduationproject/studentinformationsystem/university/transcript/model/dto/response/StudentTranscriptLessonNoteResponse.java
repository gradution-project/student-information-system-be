package com.graduationproject.studentinformationsystem.university.transcript.model.dto.response;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonCompulsoryOrElective;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentTranscriptLessonNoteResponse {

    private Long lessonId;
    private String name;
    private Integer credit;
    private LessonCompulsoryOrElective compulsoryOrElective;
    private Double meanOfNote;
    private StudentLessonNoteStatus status;
}
