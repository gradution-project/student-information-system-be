package com.graduationproject.studentinformationsystem.login.officer.common.service.impl;

import com.graduationproject.studentinformationsystem.login.officer.common.repository.OfficerLoginRepository;
import com.graduationproject.studentinformationsystem.login.officer.common.service.OfficerLoginOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficerLoginOutServiceImpl implements OfficerLoginOutService {

    private final OfficerLoginRepository officerLoginRepository;

    @Override
    public void saveOrUpdatePassword(final Long officerId, final String encodedPassword) {

        boolean isPasswordExist = officerLoginRepository.isPasswordExist(officerId);

        if (!isPasswordExist) {
            officerLoginRepository.savePassword(officerId, encodedPassword);
        } else {
            officerLoginRepository.updatePassword(officerId, encodedPassword);
        }
    }
}
