package com.graduationproject.studentinformationsystem.login.common.service;

public interface PasswordService {

    String generatePassword();

    String encodePassword(String decodedPassword);

    boolean isPasswordTrue(CharSequence rawPassword, String encodedPassword);
}
