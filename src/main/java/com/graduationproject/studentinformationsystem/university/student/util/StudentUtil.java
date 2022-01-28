package com.graduationproject.studentinformationsystem.university.student.util;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;

import java.util.List;

public class StudentUtil {

    private StudentUtil() {
    }

    /**
     * Student Email : name.surname@ogr.university.edu.tr
     */
    public static String generateStudentEmail(String name, String surname) {
        name = SisUtil.stringToStringLowerCaseWithDot(name);
        surname = SisUtil.stringToStringLowerCaseWithDot(surname);
        return name + "." + surname + "@ogr.university.edu.tr";
    }

    /**
     * Student ID : registrationYear + departmentId + studentNumber
     */
    public static Long generateStudentId(final Long departmentId, final List<Long> studentIds) {
        final String registrationYear = SisUtil.getCurrentYear();
        final Long prefixStudentId = Long.parseLong(registrationYear + departmentId);

        while (true) {
            final Long studentId = SisUtil.generateRandomIdWithPrefixId(prefixStudentId);
            if (SisUtil.isNotExistIdInIdList(studentId, studentIds)) {
                return studentId;
            }
        }
    }
}
