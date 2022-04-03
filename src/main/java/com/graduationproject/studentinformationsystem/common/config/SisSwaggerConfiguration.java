package com.graduationproject.studentinformationsystem.common.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Component
@Configuration
@ConfigurationProperties("sis")
public class SisSwaggerConfiguration {

    public static final String FACULTY_API_TAG = "Faculty Controller";
    public static final String DEPARTMENT_API_TAG = "Department Controller";
    public static final String LESSON_API_TAG = "Lesson Controller";
    public static final String EXAM_SCHEDULE_FILE_API_TAG = "Exam Schedule File Controller";
    public static final String LESSON_SCHEDULE_FILE_API_TAG = "Lesson Schedule File Controller";
    public static final String FEATURE_TOGGLE_API_TAG = "Feature Toggle Controller";
    public static final String STUDENT_API_TAG = "Student Controller";
    public static final String STUDENT_LESSON_API_TAG = "Student Lesson Controller";
    public static final String STUDENT_LESSON_REGISTRATION_API_TAG = "Student Lesson Registration Controller";
    public static final String STUDENT_LESSON_NOTE_API_TAG = "Student Lesson Note Controller";
    public static final String STUDENT_GRADUATION_API_TAG = "Student Graduation Controller";
    public static final String TEACHER_API_TAG = "Teacher Controller";
    public static final String TEACHER_LESSON_API_TAG = "Teacher Lesson Controller";
    public static final String OFFICER_API_TAG = "Officer Controller";
    public static final String LOGIN_API_TAG = "Login Controller";
    public static final String STUDENT_PASSWORD_OPERATION_API_TAG = "Student Password Operation Controller";
    public static final String TEACHER_PASSWORD_OPERATION_API_TAG = "Teacher Password Operation Controller";
    public static final String OFFICER_PASSWORD_OPERATION_API_TAG = "Officer Password Operation Controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.graduationproject.studentinformationsystem"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo())
                .tags(
                        new Tag(FACULTY_API_TAG, "UNIV_FACULTY"),
                        new Tag(DEPARTMENT_API_TAG, "UNIV_DEPARTMENT"),
                        new Tag(LESSON_API_TAG, "UNIV_LESSON"),
                        new Tag(EXAM_SCHEDULE_FILE_API_TAG, "UNIV_EXAM_SCHEDULE_FILE"),
                        new Tag(LESSON_SCHEDULE_FILE_API_TAG, "UNIV_LESSON_SCHEDULE_FILE"),
                        new Tag(FEATURE_TOGGLE_API_TAG, "UNIV_FEATURE_TOGGLE"),
                        new Tag(STUDENT_API_TAG, "STUDENT_ACADEMIC_INFO & STUDENT_PERSONAL_INFO"),
                        new Tag(STUDENT_LESSON_API_TAG, "STUDENT_LESSON"),
                        new Tag(STUDENT_LESSON_REGISTRATION_API_TAG, "STUDENT_LESSON_REGISTRATION"),
                        new Tag(STUDENT_LESSON_NOTE_API_TAG, "STUDENT_LESSON_NOTE"),
                        new Tag(STUDENT_GRADUATION_API_TAG, "STUDENT_GRADUATION"),
                        new Tag(TEACHER_API_TAG, "TEACHER_ACADEMIC_INFO & TEACHER_PERSONAL_INFO"),
                        new Tag(TEACHER_LESSON_API_TAG, "TEACHER_LESSON"),
                        new Tag(OFFICER_API_TAG, "OFFICER_ACADEMIC_INFO & OFFICER_PERSONAL_INFO"),
                        new Tag(LOGIN_API_TAG, "AUTH_STUDENT_LOGIN & AUTH_TEACHER_LOGIN & AUTH_OFFICER_LOGIN"),
                        new Tag(STUDENT_PASSWORD_OPERATION_API_TAG, "AUTH_STUDENT_PASSWORD_OPERATION"),
                        new Tag(TEACHER_PASSWORD_OPERATION_API_TAG, "AUTH_TEACHER_PASSWORD_OPERATION"),
                        new Tag(OFFICER_PASSWORD_OPERATION_API_TAG, "AUTH_OFFICER_PASSWORD_OPERATION")
                );
    }

    @Setter
    @Value("title")
    private String title;

    @Setter
    @Value("version")
    private String version;

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .build();
    }
}
