package com.graduationproject.studentinformationsystem.university.transcript.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentTranscriptControllerEndpoint {

    private StudentTranscriptControllerEndpoint() {
    }

    public static final String BASE = SisControllerEndpoint.Path.STUDENT_TRANSCRIPT;
    public static final String BY_STUDENT_ID = BASE + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
    public static final String DOWNLOAD_BY_STUDENT_ID = BASE + "/download/" + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
}
