package com.graduationproject.studentinformationsystem.university.department.util;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;

import java.util.List;

public class DepartmentUtil {

    private DepartmentUtil() {
    }

    /**
     * Department ID : facultyId + departmentNumber
     */
    public static Long generateDepartmentId(final Long facultyId, final List<Long> departmentIds) {
        while (true) {
            final Long departmentId = SisUtil.generateRandomIdWithPrefixId(facultyId);
            if (SisUtil.isNotExistIdInIdList(departmentId, departmentIds)) {
                return departmentId;
            }
        }
    }
}
