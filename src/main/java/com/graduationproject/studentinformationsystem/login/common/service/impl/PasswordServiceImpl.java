package com.graduationproject.studentinformationsystem.login.common.service.impl;

import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String generatePassword() {
        return generateRandomPassword();
    }

    @Override
    public String encodePassword(String decodedPassword) {
        return passwordEncoder.encode(decodedPassword);
    }

    @Override
    public boolean isPasswordTrue(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    protected String generateRandomPassword() {
        char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.").toCharArray();
        return RandomStringUtils.random(15, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
    }
}
