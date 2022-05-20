package com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismHoursState;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentsLessonAbsenteeismResponse extends SisBaseResponse {

    private String id;
    private Integer week;
    private StudentLessonAbsenteeismHoursState theoreticalHoursState;
    private Integer theoreticalHours;
    private StudentLessonAbsenteeismHoursState practiceHoursState;
    private Integer practiceHours;
    private StudentLessonAbsenteeismStatus status;

    private TeacherInfoResponse teacherResponse;
    private StudentInfoResponse studentResponse;
    private LessonResponse lessonResponse;
}
