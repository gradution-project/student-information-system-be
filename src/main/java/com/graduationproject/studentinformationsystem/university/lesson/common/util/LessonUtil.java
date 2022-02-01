package com.graduationproject.studentinformationsystem.university.lesson.common.util;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;

import java.util.List;

public class LessonUtil {

    private LessonUtil() {
    }

    /**
     * Lesson ID : departmentId + lessonNumber
     */
    public static Long generateLessonId(final Long departmentId, final List<Long> lessonIds) {
        while (true) {
            final Long lessonId = SisUtil.generateRandomIdWithPrefixId(departmentId);
            if (SisUtil.isNotExistIdInIdList(lessonId, lessonIds)) {
                return lessonId;
            }
        }
    }
}
