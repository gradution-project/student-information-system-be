package com.graduationproject.studentinformationsystem.university.note.model.entity;

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
    private StudentLessonNoteStatus status;
    private Long createdUserId;
    private Date createdDate;
}
