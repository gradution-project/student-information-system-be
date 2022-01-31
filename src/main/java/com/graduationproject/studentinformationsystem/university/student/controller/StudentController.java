package com.graduationproject.studentinformationsystem.university.student.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.controller.endpoint.StudentControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
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
    public ResponseEntity<SisBaseApiResponse<List<StudentInfoResponse>>> getAllStudentsByStatus(
            final StudentStatus status) {

        final List<StudentInfoResponse> infoResponses = studentService.getAllStudentsByStatus(status);
        return successResponse(infoResponses);
    }

    @GetMapping(StudentControllerEndpoint.STUDENT_ID)
    @ApiOperation(value = "Get Student Detail By Student ID")
    public ResponseEntity<SisBaseApiResponse<StudentInfoDetailResponse>> getStudentDetailById(
            @PathVariable final Long studentId)
            throws SisNotExistException {

        final StudentInfoDetailResponse infoDetailResponse = studentService.getStudentDetailById(studentId);
        return successResponse(infoDetailResponse);
    }

    @PostMapping(StudentControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Student")
    public ResponseEntity<SisBaseApiResponse<StudentInfoDetailResponse>> saveStudent(
            @RequestBody @Valid final StudentSaveRequest saveRequest) {

        final StudentInfoDetailResponse infoDetailResponse = studentService.saveStudent(saveRequest);
        return successResponse(infoDetailResponse);
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_ACADEMIC_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Academic Info")
    public ResponseEntity<SisBaseApiResponse<StudentAcademicInfoResponse>> updateStudentAcademicInfo(
            @PathVariable final Long studentId,
            @RequestBody @Valid final StudentAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        final StudentAcademicInfoResponse academicInfoResponse = studentService.updateStudentAcademicInfo(studentId, academicInfoUpdateRequest);
        return successResponse(academicInfoResponse);
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_PERSONAL_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Personal Info")
    public ResponseEntity<SisBaseApiResponse<StudentPersonalInfoResponse>> updateStudentPersonalInfo(
            @PathVariable final Long studentId,
            @RequestBody @Valid final StudentPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException {

        final StudentPersonalInfoResponse personalInfoResponse = studentService.updateStudentPersonalInfo(studentId, personalInfoUpdateRequest);
        return successResponse(personalInfoResponse);
    }

    @DeleteMapping(StudentControllerEndpoint.DELETE)
    @ApiOperation(value = "Delete Student")
    public ResponseEntity<SisBaseApiResponse<StudentInfoResponse>> deleteStudent(
            @RequestBody @Valid final StudentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentInfoResponse infoResponse = studentService.deleteStudent(deleteRequest);
        return successResponse(infoResponse);
    }

    @PatchMapping(StudentControllerEndpoint.PASSIVATE)
    @ApiOperation(value = "Passivate Student")
    public ResponseEntity<SisBaseApiResponse<StudentInfoResponse>> passivateStudent(
            @RequestBody @Valid final StudentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentInfoResponse infoResponse = studentService.passivateStudent(passivateRequest);
        return successResponse(infoResponse);
    }

    @PatchMapping(StudentControllerEndpoint.ACTIVATE)
    @ApiOperation(value = "Activate Student")
    public ResponseEntity<SisBaseApiResponse<StudentInfoResponse>> activateStudent(
            @RequestBody @Valid final StudentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentInfoResponse infoResponse = studentService.activateStudent(activateRequest);
        return successResponse(infoResponse);
    }

    @PatchMapping(StudentControllerEndpoint.GRADUATE)
    @ApiOperation(value = "Graduate Student")
    public ResponseEntity<SisBaseApiResponse<StudentInfoResponse>> graduateStudent(
            @RequestBody @Valid final StudentGraduateRequest graduateRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentInfoResponse infoResponse = studentService.graduateStudent(graduateRequest);
        return successResponse(infoResponse);
    }
}
