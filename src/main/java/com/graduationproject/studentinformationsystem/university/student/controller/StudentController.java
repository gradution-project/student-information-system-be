package com.graduationproject.studentinformationsystem.university.student.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.controller.endpoint.StudentControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.university.student.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.STUDENT;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(STUDENT)
@Api(tags = STUDENT_API_TAG)
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @ApiOperation(value = "Get All Student By Status")
    public ResponseEntity<SisBaseApiResponse<List<StudentResponse>>> getAllStudentsByStatus(StudentStatus status) {

        List<StudentResponse> studentResponseList = studentService.getAllStudentsByStatus(status);
        return successResponse(studentResponseList);
    }

    @GetMapping(StudentControllerEndpoint.STUDENT_ID)
    @ApiOperation(value = "Get Student Detail By Student ID")
    public ResponseEntity<SisBaseApiResponse<StudentInfoDetailResponse>> getStudentDetailById(@PathVariable Long studentId)
            throws SisNotExistException {

        StudentInfoDetailResponse studentInfoDetailResponse = studentService.getStudentDetailById(studentId);
        return successResponse(studentInfoDetailResponse);
    }

    @PostMapping(StudentControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Student")
    public ResponseEntity<SisBaseApiResponse<StudentInfoDetailResponse>> saveStudent(
            @RequestBody @Valid StudentSaveInfoRequest saveInfoRequest) {

        StudentInfoDetailResponse studentInfoDetailResponse = studentService.saveStudent(saveInfoRequest);
        return successResponse(studentInfoDetailResponse);
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_ACADEMIC_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Academic Info")
    public ResponseEntity<SisBaseApiResponse<StudentAcademicInfoResponse>> updateStudentAcademicInfo(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentUpdateAcademicInfoRequest updateAcademicInfoRequest)
            throws SisNotExistException {

        return successResponse(studentService.updateStudentAcademicInfo(studentId, updateAcademicInfoRequest));
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_PERSONAL_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Personal Info")
    public ResponseEntity<SisBaseApiResponse<StudentPersonalInfoResponse>> updateStudentPersonalInfo(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentUpdatePersonalInfoRequest updatePersonalInfoRequest)
            throws SisNotExistException {

        return successResponse(studentService.updateStudentPersonalInfo(studentId, updatePersonalInfoRequest));
    }

    @DeleteMapping(StudentControllerEndpoint.DELETE_BY_STUDENT_ID)
    @ApiOperation(value = "Delete Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> deleteStudent(
            @RequestBody @Valid StudentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.deleteStudent(deleteRequest));
    }

    @PatchMapping(StudentControllerEndpoint.PASSIVATE_BY_STUDENT_ID)
    @ApiOperation(value = "Passivate Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> passivateStudent(
            @RequestBody @Valid StudentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.passivateStudent(passivateRequest));
    }

    @PatchMapping(StudentControllerEndpoint.ACTIVATE_BY_STUDENT_ID)
    @ApiOperation(value = "Activate Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> activateStudent(
            @RequestBody @Valid StudentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.activateStudent(activateRequest));
    }

    @PatchMapping(StudentControllerEndpoint.GRADUATE_BY_STUDENT_ID)
    @ApiOperation(value = "Graduate Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> graduateStudent(
            @RequestBody @Valid StudentGraduateRequest graduateRequest)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.graduateStudent(graduateRequest));
    }
}
