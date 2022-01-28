package com.graduationproject.studentinformationsystem.login.common.service;

public interface PasswordService {

    String generatePassword();

    String encodePassword(final String decodedPassword);

    boolean isPasswordTrue(final CharSequence rawPassword, final String encodedPassword);
}
