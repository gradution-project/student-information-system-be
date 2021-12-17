package com.graduationproject.studentinformationsystem.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SisSwaggerConfiguration {

    public static final String LOGIN_API_TAG = "Login Controller";
    public static final String STUDENT_API_TAG = "Student Controller";
    public static final String TEACHER_API_TAG = "Teacher Controller";
    public static final String LESSON_API_TAG = "Lesson Controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.graduationproject.studentinformationsystem"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo())
                .tags(
                        new Tag(LOGIN_API_TAG, "AUTH_STUDENT_LOGIN & AUTH_TEACHER_LOGIN & AUTH_OFFICER_LOGIN"),
                        new Tag(STUDENT_API_TAG, "STUDENT_ACADEMIC_INFO & STUDENT_PERSONAL_INFO"),
                        new Tag(TEACHER_API_TAG, "TEACHER_ACADEMIC_INFO & TEACHER_PERSONAL_INFO"),
                        new Tag(LESSON_API_TAG, "UNIV_LESSON & TEACHER_LESSON")
                );
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("Student Information System API")
                .version("v1.0.0")
                .build();
    }
}
