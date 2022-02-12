package com.graduationproject.studentinformationsystem.university.student.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentDegree {

    ASSOCIATE("Önlisans"),
    UNDERGRADUATE("Lisans"),
    POSTGRADUATE("Yüksek Lisans"),
    DOCTORAL("Doktora");

    private final String tr;
}
