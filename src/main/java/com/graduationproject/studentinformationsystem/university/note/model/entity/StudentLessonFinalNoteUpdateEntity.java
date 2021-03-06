package com.graduationproject.studentinformationsystem.university.note.model.entity;

import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteState;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StudentLessonFinalNoteUpdateEntity {

    private String id;
    private Double finalNote;
    private StudentLessonNoteState finalNoteState;
    private Double meanOfNote;
    private StudentLessonNoteStatus status;
    private Long modifiedUserId;
    private Date modifiedDate;
}
