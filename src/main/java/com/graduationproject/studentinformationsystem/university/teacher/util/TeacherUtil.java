package com.graduationproject.studentinformationsystem.university.teacher.util;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;

import java.util.List;

public class TeacherUtil {

    private TeacherUtil() {
    }

    /**
     * Teacher Email : name.surname@university.edu.tr
     */
    public static String generateTeacherEmail(String name, String surname) {
        name = SisUtil.stringToStringLowerCaseWithDot(name);
        surname = SisUtil.stringToStringLowerCaseWithDot(surname);
        return name + "." + surname + "@university.edu.tr";
    }

    /**
     * Teacher ID : departmentId + teacherNumber
     */
    public static Long generateTeacherId(final Long departmentId, final List<Long> teacherIds) {
        while (true) {
            final Long teacherId = SisUtil.generateRandomIdWithPrefixId(departmentId);
            if (SisUtil.isNotExistIdInIdList(teacherId, teacherIds)) {
                return teacherId;
            }
        }
    }
}
