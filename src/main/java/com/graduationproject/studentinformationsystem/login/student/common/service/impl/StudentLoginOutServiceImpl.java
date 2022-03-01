package com.graduationproject.studentinformationsystem.login.student.common.service.impl;

import com.graduationproject.studentinformationsystem.login.student.common.repository.StudentLoginRepository;
import com.graduationproject.studentinformationsystem.login.student.common.service.StudentLoginOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentLoginOutServiceImpl implements StudentLoginOutService {

    private final StudentLoginRepository studentLoginRepository;

    @Override
    public void saveOrUpdatePassword(final Long studentId, final String encodedPassword) {

        boolean isPasswordExist = studentLoginRepository.isPasswordExist(studentId);

        if (!isPasswordExist) {
            studentLoginRepository.savePassword(studentId, encodedPassword);
        } else {
            studentLoginRepository.updatePassword(studentId, encodedPassword);
        }
    }
}
