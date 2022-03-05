package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;

public interface OfficerMailService {

    void sendSavedEmail(final OfficerInfoDetailResponse infoDetailResponse);

    void sendForgotPasswordEmail(final OfficerInfoDetailResponse infoDetailResponse);
}
