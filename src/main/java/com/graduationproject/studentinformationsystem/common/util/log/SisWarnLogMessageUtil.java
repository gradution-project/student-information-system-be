package com.graduationproject.studentinformationsystem.common.util.log;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@RequiredArgsConstructor
public class SisWarnLogMessageUtil {

    private final String apiName;


    public void notFoundById(final Long id) {
        log.warn("{} Not Found by ID! id:{}", apiName, id);
    }

    public void notFoundById(final String id) {
        log.warn("{} Not Found by ID! id:{}", apiName, id);
    }

    public void notFoundByDepartmentId(final Long departmentId) {
        log.warn("{} Not Found by Department ID! departmentId:{}", apiName, departmentId);
    }

    public void notFoundByName(final String name) {
        log.warn("{} Not Found by Name! name:{}", apiName, name);
    }

    public void notFoundByIdAndStatus(final Long id, final String status) {
        log.warn("{} Not Found by ID! id:{} status:{}", apiName, id, status);
    }

    public void notFoundAllByStudentId(final Long lessonId) {
        log.warn("{}s Not Found By Student ID! lessonId:{}", apiName, lessonId);
    }

    public void notFoundAllByLessonId(final Long lessonId) {
        log.warn("{}s Not Found By Lesson ID! lessonId:{}", apiName, lessonId);
    }

    public void notFoundAllIds() {
        log.warn("{} IDs Not Found!", apiName);
    }

    public void notFoundAllIdsByStatus(final String status) {
        log.warn("{}s Not Found by Status! status:{}", apiName, status);
    }

    public void notFoundAllIdsByDepartmentId(final Long departmentId) {
        log.warn("{} IDs Not Found by Department ID! departmentId:{}", apiName, departmentId);
    }

    public void notFoundAllIdsByFacultyId(final Long facultyId) {
        log.warn("{} IDs Not Found by Faculty ID! facultyId:{}", apiName, facultyId);
    }
}
