package com.graduationproject.studentinformationsystem.university.absenteeism.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentsLessonAbsenteeismUpdateRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentLessonAbsenteeismResponse;

import java.util.List;

public interface StudentLessonAbsenteeismService {

    List<StudentLessonAbsenteeismResponse> getAllStudentLessonsAbsenteeismByStudentId(Long studentId, Integer week)
            throws SisNotExistException;

    List<StudentLessonAbsenteeismResponse> getAllStudentsLessonsAbsenteeismByLessonId(Long lessonId, Integer week)
            throws SisNotExistException;

    List<StudentLessonAbsenteeismResponse> updateStudentLessonAbsenteeism(StudentsLessonAbsenteeismUpdateRequest updateRequest)
            throws SisNotExistException, SisProcessException;
}
