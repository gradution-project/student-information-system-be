package com.graduationproject.studentinformationsystem.university.note.model.entity;

import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteState;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StudentLessonMidtermNoteUpdateEntity {

    private String id;
    private Double midtermNote;
    private StudentLessonNoteState midtermNoteState;
    private Long modifiedUserId;
    private Date modifiedDate;
}
