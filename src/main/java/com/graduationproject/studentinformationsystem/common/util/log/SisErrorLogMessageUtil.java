package com.graduationproject.studentinformationsystem.common.util.log;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@RequiredArgsConstructor
public class SisErrorLogMessageUtil {

    private final String apiName;


    public void errorWhenGetting() {
        log.error("Error When Getting {}!", apiName);
    }

    public void errorWhenGettingById(final Long id) {
        log.error("Error When Getting {} by ID! id:{}", apiName, id);
    }

    public void errorWhenGettingByName(final String name) {
        log.error("Error When Getting {} by Name! name:{}", apiName, name);
    }

    public void errorWhenGettingAll() {
        log.error("Error When Getting {}s!", apiName);
    }

    public void errorWhenGettingAllIds() {
        log.error("Error When Getting {} IDs!", apiName);
    }

    public void errorWhenGettingAllByStatus(final String status) {
        log.error("Error When Getting {}s by Status! status:{}", apiName, status);
    }

    public void errorWhenGettingAllIdsByDepartmentId(final Long departmentId) {
        log.error("Error When Getting {} IDs by Department ID! departmentId:{}", apiName, departmentId);
    }

    public void errorWhenGettingAllIdsByFacultyId(final Long facultyId) {
        log.error("Error When Getting {} IDs by Faculty ID! facultyId:{}", apiName, facultyId);
    }


    public void errorWhenSaving() {
        log.error("Error When Saving {}!", apiName);
    }

    public void errorWhenUpdating() {
        log.error("Error When Updating {}!", apiName);
    }

    public void errorWhenUpdatingStatus() {
        log.error("Error When Updating {} Status!", apiName);
    }

    public void errorWhenDeleting() {
        log.error("Error When Deleting {}!", apiName);
    }
}
