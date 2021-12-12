package com.graduationproject.studentinformationsystem.university.parameter.repository;

public interface ParameterRepository {

    String getStudentParameterByName(String name);

    String getTeacherParameterByName(String name);

    String getOfficerParameterByName(String name);
}
