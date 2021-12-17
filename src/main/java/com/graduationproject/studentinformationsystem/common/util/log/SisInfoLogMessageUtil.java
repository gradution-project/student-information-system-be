package com.graduationproject.studentinformationsystem.common.util.log;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@RequiredArgsConstructor
public class SisInfoLogMessageUtil {

    private final String apiName;


    public void foundById(Long id) {
        log.info("{} Found by ID! id:{}", apiName, id);
    }

    public void foundByName(String name) {
        log.info("{} Found by Name! name:{}", apiName, name);
    }

    public void foundByIdAndStatus(Long id, String status) {
        log.info("{} Found by ID and Status! id:{} status:{}", apiName, id, status);
    }

    public void foundAll() {
        log.info("{}s Found!", apiName);
    }

    public void foundAllByStatus(String status) {
        log.info("{}s Found by Status! status:{}", apiName, status);
    }

    public void foundAllIdsByDepartmentId(Long departmentId) {
        log.info("{} IDs Found by Department ID! departmentId:{}", apiName, departmentId);
    }


    public void savedById(Long id) {
        log.info("{} Saved! id:{}", apiName, id);
    }

    public void updated() {
        log.info("{} Updated!", apiName);
    }

    public void statusUpdated(String status) {
        log.info("{} Status Updated to {}!", apiName, status);
    }

    public void deleted() {
        log.info("{} Deleted!", apiName);
    }
}
