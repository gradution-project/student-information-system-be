package com.graduationproject.studentinformationsystem.student.controller;

import com.graduationproject.studentinformationsystem.common.util.group.OnCreate;
import com.graduationproject.studentinformationsystem.common.util.group.OnUpdate;
import com.graduationproject.studentinformationsystem.common.util.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.student.controller.endpoint.StudentControllerEndpoint;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentResponse;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.STUDENT;
import static com.graduationproject.studentinformationsystem.common.util.response.SisBaseResponseUtil.successResponse;

@RestController
@RequestMapping(STUDENT)
@Api(tags = STUDENT_API_TAG)
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @ApiOperation(value = "Get All Student By Status")
    public ResponseEntity<SisBaseResponse<List<StudentResponse>>> getAllStudentsByStatus(StudentStatus status) {

        List<StudentResponse> studentResponseList = studentService.getAllStudentsByStatus(status);
        return successResponse(studentResponseList);
    }

    @GetMapping(StudentControllerEndpoint.STUDENT_ID)
    @ApiOperation(value = "Get Student Detail By Student ID")
    public ResponseEntity<SisBaseResponse<StudentInfoDetailResponse>> getStudentDetailById(@PathVariable Long studentId) {

        StudentInfoDetailResponse studentInfoDetailResponse = studentService.getStudentDetailById(studentId);
        return successResponse(studentInfoDetailResponse);
    }

    @PostMapping(StudentControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Student")
    public ResponseEntity<SisBaseResponse<StudentInfoDetailResponse>> saveStudent(
            @RequestBody @Validated(OnCreate.class) StudentInfoRequest studentInfoRequest) {

        StudentInfoDetailResponse studentInfoDetailResponse = studentService.saveStudent(studentInfoRequest);
        return successResponse(studentInfoDetailResponse);
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_ACADEMIC_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Academic Info")
    public ResponseEntity<SisBaseResponse<StudentAcademicInfoResponse>> updateStudentAcademicInfo(
            @PathVariable Long studentId,
            @RequestBody @Validated(OnUpdate.class) StudentAcademicInfoRequest academicInfoRequest) {

        return successResponse(studentService.updateStudentAcademicInfo(studentId, academicInfoRequest));
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_PERSONAL_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Personal Info")
    public ResponseEntity<SisBaseResponse<StudentPersonalInfoResponse>> updateStudentPersonalInfo(
            @PathVariable Long studentId,
            @RequestBody @Validated(OnUpdate.class) StudentPersonalInfoRequest personalInfoRequest) {

        return successResponse(studentService.updateStudentPersonalInfo(studentId, personalInfoRequest));
    }

    @DeleteMapping(StudentControllerEndpoint.DELETE_BY_STUDENT_ID)
    @ApiOperation(value = "Delete Student")
    public ResponseEntity<SisBaseResponse<StudentResponse>> deleteStudent(@PathVariable Long studentId) {
        return successResponse(studentService.deleteStudent(studentId));
    }

    @PatchMapping(StudentControllerEndpoint.PASSIVATE_BY_STUDENT_ID)
    @ApiOperation(value = "Passivate Student")
    public ResponseEntity<SisBaseResponse<StudentResponse>> passivateStudent(@PathVariable Long studentId) {
        return successResponse(studentService.passivateStudent(studentId));
    }

    @PatchMapping(StudentControllerEndpoint.ACTIVATE_BY_STUDENT_ID)
    @ApiOperation(value = "Activate Student")
    public ResponseEntity<SisBaseResponse<StudentResponse>> activateStudent(@PathVariable Long studentId) {
        return successResponse(studentService.activateStudent(studentId));
    }

    @PatchMapping(StudentControllerEndpoint.GRADUATE_BY_STUDENT_ID)
    @ApiOperation(value = "Graduate Student")
    public ResponseEntity<SisBaseResponse<StudentResponse>> graduateStudent(@PathVariable Long studentId) {
        return successResponse(studentService.graduateStudent(studentId));
    }
}
