package com.graduationproject.studentinformationsystem.university.absenteeism.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentsLessonAbsenteeismUpdateRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentLessonsAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentsLessonAbsenteeismResponse;

import java.text.ParseException;
import java.util.List;

public interface StudentLessonAbsenteeismService {

    List<StudentLessonsAbsenteeismResponse> getAllStudentLessonsAbsenteeismByStudentId(Long studentId)
            throws SisNotExistException;

    List<StudentsLessonAbsenteeismResponse> getAllStudentsLessonAbsenteeismByLessonId(Long lessonId, Integer week)
            throws SisNotExistException;

    List<StudentsLessonAbsenteeismResponse> updateStudentLessonAbsenteeism(StudentsLessonAbsenteeismUpdateRequest updateRequest)
            throws SisNotExistException, SisProcessException;

    Integer getTotalLessonAbsenteeismWeek() throws ParseException, SisNotExistException, SisProcessException;
}
