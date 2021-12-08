package com.graduationproject.studentinformationsystem.student.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
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
            @RequestBody @Valid StudentInfoRequest studentInfoRequest) {

        StudentInfoDetailResponse studentInfoDetailResponse = studentService.saveStudent(studentInfoRequest);
        return successResponse(studentInfoDetailResponse);
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_ACADEMIC_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Academic Info")
    public ResponseEntity<SisBaseApiResponse<StudentAcademicInfoResponse>> updateStudentAcademicInfo(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentAcademicInfoRequest academicInfoRequest)
            throws SisNotExistException {

        return successResponse(studentService.updateStudentAcademicInfo(studentId, academicInfoRequest));
    }

    @PutMapping(StudentControllerEndpoint.UPDATE_PERSONAL_INFO_BY_STUDENT_ID)
    @ApiOperation(value = "Update Student Personal Info")
    public ResponseEntity<SisBaseApiResponse<StudentPersonalInfoResponse>> updateStudentPersonalInfo(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentPersonalInfoRequest personalInfoRequest)
            throws SisNotExistException {

        return successResponse(studentService.updateStudentPersonalInfo(studentId, personalInfoRequest));
    }

    @DeleteMapping(StudentControllerEndpoint.DELETE_BY_STUDENT_ID)
    @ApiOperation(value = "Delete Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> deleteStudent(@PathVariable Long studentId)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.deleteStudent(studentId));
    }

    @PatchMapping(StudentControllerEndpoint.PASSIVATE_BY_STUDENT_ID)
    @ApiOperation(value = "Passivate Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> passivateStudent(@PathVariable Long studentId)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.passivateStudent(studentId));
    }

    @PatchMapping(StudentControllerEndpoint.ACTIVATE_BY_STUDENT_ID)
    @ApiOperation(value = "Activate Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> activateStudent(@PathVariable Long studentId)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.activateStudent(studentId));
    }

    @PatchMapping(StudentControllerEndpoint.GRADUATE_BY_STUDENT_ID)
    @ApiOperation(value = "Graduate Student")
    public ResponseEntity<SisBaseApiResponse<StudentResponse>> graduateStudent(@PathVariable Long studentId)
            throws SisNotExistException, SisAlreadyException {

        return successResponse(studentService.graduateStudent(studentId));
    }
}
