package com.graduationproject.studentinformationsystem.university.note.model.entity;

import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StudentLessonResitNoteUpdateEntity {

    private String id;
    private Double resitNote;
    private Double meanOfNote;
    private StudentLessonNoteStatus status;
    private Long modifiedUserId;
    private Date modifiedDate;
}
