package com.graduationproject.studentinformationsystem.common.util.log;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@RequiredArgsConstructor
public class SisWarnLogMessageUtil {

    private final String apiName;


    public void notFoundById(Long id) {
        log.warn("{} Not Found by ID! id:{}", apiName, id);
    }
}
