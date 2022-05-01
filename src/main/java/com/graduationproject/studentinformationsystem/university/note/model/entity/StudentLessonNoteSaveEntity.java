package com.graduationproject.studentinformationsystem.university.note.model.entity;

import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteState;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StudentLessonNoteSaveEntity {

    private String id;
    private Long teacherId;
    private Long studentId;
    private Long lessonId;
    private StudentLessonNoteState midtermNoteState;
    private StudentLessonNoteState finalNoteState;
    private StudentLessonNoteState resitNoteState;
    private StudentLessonNoteStatus status;
    private Long createdUserId;
    private Date createdDate;
}
