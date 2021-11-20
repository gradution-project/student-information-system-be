package com.graduationproject.studentinformationsystem.student.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentStatus {

    ACTIVE('A'),
    PASSIVE('P'),
    DELETED('D'),
    GRADUATED('G');

    private final Character character;
}
