package com.graduationproject.studentinformationsystem.university.student.util;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class StudentUtil {

    private StudentUtil() {
    }

    /**
     * Student Email : name.surname@ogr.university.edu.tr
     */
    public static String generateStudentEmail(String name, String surname) {
        name = stringToStringLowerCaseWithDot(name);
        surname = stringToStringLowerCaseWithDot(surname);
        return name + "." + surname + "@ogr.university.edu.tr";
    }

    private static String stringToStringLowerCaseWithDot(String string) {
        string = string.toLowerCase(Locale.ROOT);

        String[] stringArray = string.split(" ");
        if (stringArray.length >= 2) {
            return string.replace(" ", ".");
        }
        return string;
    }

    /**
     * Student ID : registrationYear + departmentId + studentNumber
     */
    public static Long generateStudentId(Long departmentId, List<Long> studentIdList) {
        String registrationYear = SisUtil.getCurrentYear();
        Long prefixStudentId = Long.parseLong(registrationYear + departmentId);
        Random random = new Random();

        while (true) {
            Long studentId = generateRandomStudentId(random, prefixStudentId);
            if (studentIdCheck(studentId, studentIdList)) {
                return studentId;
            }
        }
    }

    private static Long generateRandomStudentId(Random random, Long prefixStudentId) {
        String stringNumber = String.valueOf(random.nextLong(999));
        stringNumber = switch (stringNumber.length()) {
            case 1 -> "00" + stringNumber;
            case 2 -> "0" + stringNumber;
            default -> stringNumber;
        };
        return Long.parseLong(prefixStudentId + stringNumber);
    }

    private static boolean studentIdCheck(Long studentId, List<Long> studentIdList) {
        for (Long studentIdFromDb : studentIdList) {
            if (studentId.equals(studentIdFromDb)) {
                return false;
            }
        }
        return true;
    }
}
