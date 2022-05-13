package com.graduationproject.studentinformationsystem.university.officer.util;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;

import java.util.List;

public class OfficerUtil {

    private OfficerUtil() {
    }

    /**
     * Officer Email : name.surname@university.edu.tr
     */
    public static String generateOfficerEmail(String name, String surname) {
        name = SisUtil.setStringToStringLowerCaseAndChangeSpacesWithChar(name, ".");
        surname = SisUtil.setStringToStringLowerCaseAndChangeSpacesWithChar(surname, ".");
        return name + "." + surname + "@university.edu.tr";
    }

    /**
     * Officer ID : facultyId + officerNumber
     */
    public static Long generateOfficerId(final Long facultyId, final List<Long> officerIds) {
        while (true) {
            final Long officerId = SisUtil.generateRandomIdWithPrefixId(facultyId);
            if (SisUtil.isNotExistIdInIdList(officerId, officerIds)) {
                return officerId;
            }
        }
    }
}
