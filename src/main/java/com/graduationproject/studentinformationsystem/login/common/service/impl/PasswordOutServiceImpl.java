package com.graduationproject.studentinformationsystem.login.common.service.impl;

import com.graduationproject.studentinformationsystem.login.common.service.PasswordOutService;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordOutServiceImpl implements PasswordOutService {

    private final PasswordService passwordService;

    @Override
    public String getEncodedPassword(String newPassword) {
        return passwordService.encodePassword(newPassword);
    }
}
