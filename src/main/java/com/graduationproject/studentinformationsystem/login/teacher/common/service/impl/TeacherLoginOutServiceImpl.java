package com.graduationproject.studentinformationsystem.login.teacher.common.service.impl;

import com.graduationproject.studentinformationsystem.login.teacher.common.repository.TeacherLoginRepository;
import com.graduationproject.studentinformationsystem.login.teacher.common.service.TeacherLoginOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherLoginOutServiceImpl implements TeacherLoginOutService {

    private final TeacherLoginRepository teacherLoginRepository;

    @Override
    public void saveOrUpdatePassword(final Long teacherId, final String encodedPassword) {

        boolean isPasswordExist = teacherLoginRepository.isPasswordExist(teacherId);

        if (!isPasswordExist) {
            teacherLoginRepository.savePassword(teacherId, encodedPassword);
        } else {
            teacherLoginRepository.updatePassword(teacherId, encodedPassword);
        }
    }
}
