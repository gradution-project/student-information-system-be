package com.graduationproject.studentinformationsystem.university.teacher.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeacherDegree {
    RESEARCH_ASSOCIATE("Araştırma Görevlisi"),
    TEACHING_ASSOCIATE("Öğretim üyesi"),
    ASSISTANT_PROFESSOR("Doçent"),
    PROFESSOR("Profesör");

    private final String name;
}
