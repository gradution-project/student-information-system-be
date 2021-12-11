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

    public void errorWhenGettingById(Long id) {
        log.error("Error When Getting {} by ID! id:{}", apiName, id);
    }

    public void errorWhenGettingByName(String name) {
        log.error("Error When Getting {} by Name! name:{}", apiName, name);
    }

    public void errorWhenGettingAllByStatus(String status) {
        log.error("Error When Getting {}s by Status! status:{}", apiName, status);
    }

    public void errorWhenGettingAllIdsByDepartmentId(Long departmentId) {
        log.error("Error When Getting {} IDs by Department ID! departmentId:{}", apiName, departmentId);
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
}
