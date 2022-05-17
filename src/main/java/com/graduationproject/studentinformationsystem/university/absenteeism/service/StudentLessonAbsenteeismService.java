package com.graduationproject.studentinformationsystem.university.absenteeism.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentsLessonAbsenteeismUpdateRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentsLessonAbsenteeismResponse;

import java.text.ParseException;
import java.util.List;

public interface StudentLessonAbsenteeismService {

    List<StudentsLessonAbsenteeismResponse> getAllStudentLessonsAbsenteeismByStudentId(Long studentId, Integer week)
            throws SisNotExistException;

    List<StudentsLessonAbsenteeismResponse> getAllStudentsLessonsAbsenteeismByLessonId(Long lessonId, Integer week)
            throws SisNotExistException;

    List<StudentsLessonAbsenteeismResponse> updateStudentLessonAbsenteeism(StudentsLessonAbsenteeismUpdateRequest updateRequest)
            throws SisNotExistException, SisProcessException;

    Integer getTotalLessonAbsenteeismWeek() throws ParseException, SisNotExistException, SisProcessException;
}
