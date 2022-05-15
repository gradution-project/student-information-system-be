package com.graduationproject.studentinformationsystem.university.absenteeism.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;

import java.text.ParseException;
import java.util.List;

public interface StudentLessonAbsenteeismOutService {

    void saveStudentLessonAbsenteeism(Long studentId,
                                      List<LessonResponse> lessonResponses,
                                      SisOperationInfoRequest operationInfoRequest) throws SisNotExistException, ParseException, SisProcessException;
}
