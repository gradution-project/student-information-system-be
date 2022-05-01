package com.graduationproject.studentinformationsystem.university.note.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteState;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentLessonNoteEntity extends SisBaseEntity {

    private String id;
    private Long teacherId;
    private Long studentId;
    private Long lessonId;
    private Double midtermNote;
    private StudentLessonNoteState midtermNoteState;
    private Double finalNote;
    private StudentLessonNoteState finalNoteState;
    private Double resitNote;
    private StudentLessonNoteState resitNoteState;
    private Double meanOfNote;
    private StudentLessonNoteStatus status;
}
