package com.graduationproject.studentinformationsystem.university.lesson.teacher.service.impl;

import com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.TeacherLessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherLessonOutServiceImpl implements TeacherLessonOutService {

    private final TeacherLessonRepository teacherLessonRepository;

    @Override
    public Long getTeacherIdByLessonId(Long lessonId) {
        return teacherLessonRepository.getTeacherIdByLessonId(lessonId);
    }
}
