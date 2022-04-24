package com.graduationproject.studentinformationsystem.university.note.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteState;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentLessonNoteResponse extends SisBaseResponse {

    private String id;
    private Double midtermNote;
    private StudentLessonNoteState midtermNoteState;
    private Double finalNote;
    private StudentLessonNoteState finalNoteState;
    private Double resitNote;
    private StudentLessonNoteState resitNoteState;
    private Double meanOfNote;
    private StudentLessonNoteStatus status;

    private TeacherInfoResponse teacherResponse;
    private StudentInfoResponse studentResponse;
    private LessonResponse lessonResponse;
}
