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
}