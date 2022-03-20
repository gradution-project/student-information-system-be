package com.graduationproject.studentinformationsystem.university.note.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StudentLessonNoteMapping {

    ID("ID", "id"),
    TEACHER_ID("TEACHER_ID", "teacherId"),
    STUDENT_ID("STUDENT_ID", "studentId"),
    LESSON_ID("LESSON_ID", "lessonId"),
    MIDTERM_NOTE("MIDTERM_NOTE", "midtermNote"),
    FINAL_NOTE("FINAL_NOTE", "finalNote"),
    RESIT_NOTE("RESIT_NOTE", "resitNote"),
    MEAN_OF_NOTE("MEAN_OF_NOTE", "meanOfNote"),
    STATUS("STATUS", "status"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(StudentLessonNoteMapping::getColumnName, StudentLessonNoteMapping::getModelName));
}
