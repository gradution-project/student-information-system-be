package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
public class StudentLessonRegistrationInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -818997554323584139L;

    @NotNull
    @StudentID
    private Long studentId;

    @NotNull
    @NotEmpty
    private List<Long> lessonsIds;
}
