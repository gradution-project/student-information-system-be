package com.graduationproject.studentinformationsystem.teacher.util;

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
    public static Long generateTeacherId(Long departmentId, List<Long> teacherIdList) {
        while (true) {
            Long teacherId = SisUtil.generateRandomIdWithPrefixId(departmentId);
            if (!SisUtil.isExistIdInIdList(teacherId, teacherIdList)) {
                return teacherId;
            }
        }
    }
}
