package com.graduationproject.studentinformationsystem.university.faculty.util;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;

import java.util.List;
import java.util.Random;

public class FacultyUtil {

    private FacultyUtil() {
    }

    /**
     * Faculty ID : facultyNumber
     */
    public static Long generateFacultyId(final List<Long> facultyIds) {
        while (true) {
            final Long facultyId = generateRandomFacultyId();
            if (SisUtil.isNotExistIdInIdList(facultyId, facultyIds)) {
                return facultyId;
            }
        }
    }

    private static Long generateRandomFacultyId() {
        final Random random = new Random();
        final String number = String.valueOf(random.nextLong(10, 99));
        return Long.parseLong(number);
    }
}
