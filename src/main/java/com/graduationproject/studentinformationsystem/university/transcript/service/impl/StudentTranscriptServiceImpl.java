package com.graduationproject.studentinformationsystem.university.transcript.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.file.model.SisColor;
import com.graduationproject.studentinformationsystem.file.model.enums.Phenomena;
import com.graduationproject.studentinformationsystem.file.service.SisFontGeneratorService;
import com.graduationproject.studentinformationsystem.file.service.SisPdfComponentGeneratorService;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonSemester;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import com.graduationproject.studentinformationsystem.university.note.service.StudentLessonNoteOutService;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import com.graduationproject.studentinformationsystem.university.transcript.model.dto.converter.StudentTranscriptInfoConverter;
import com.graduationproject.studentinformationsystem.university.transcript.model.dto.response.StudentTranscriptLessonNoteResponse;
import com.graduationproject.studentinformationsystem.university.transcript.model.dto.response.StudentTranscriptLessonNoteSemesterResponse;
import com.graduationproject.studentinformationsystem.university.transcript.model.dto.response.StudentTranscriptResponse;
import com.graduationproject.studentinformationsystem.university.transcript.model.exception.StudentTranscriptException;
import com.graduationproject.studentinformationsystem.university.transcript.service.StudentTranscriptService;
import com.graduationproject.studentinformationsystem.university.transcript.util.StudentTranscriptUtil;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentTranscriptServiceImpl implements StudentTranscriptService {

    private final StudentOutService studentOutService;
    private final StudentLessonNoteOutService studentLessonNoteOutService;
    private final SisPdfComponentGeneratorService pdfGeneratorService;
    private final SisFontGeneratorService fontGeneratorService;

    private final StudentTranscriptInfoConverter studentTranscriptInfoConverter;

    @Override
    public StudentTranscriptResponse getStudentTranscriptDetailByStudentId(final Long studentId)
            throws SisNotExistException, SisAlreadyException {

        ifStudentIsNotExistThrowNotExistException(studentId);

        final List<StudentLessonNoteResponse> studentLessonNoteResponses = studentLessonNoteOutService
                .getStudentLessonsAllConfirmedNotesByStudentId(studentId);

        final Map<LessonSemester, List<StudentTranscriptLessonNoteResponse>> studentLessonsSemestersNotesResponse = studentTranscriptInfoConverter
                .generateStudentLessonsSemestersNotesResponseMap(studentLessonNoteResponses);

        if (MapUtils.isEmpty(studentLessonsSemestersNotesResponse)) {
            StudentTranscriptException.throwNotExistsException(studentId);
        }
        final StudentInfoResponse studentInfoResponse = studentLessonNoteResponses.get(0).getStudentResponse();
        return calculateAndSetMeanOfAllNotesAndGenerateStudentTranscriptResponse(studentInfoResponse, studentLessonsSemestersNotesResponse);
    }

    private StudentTranscriptResponse calculateAndSetMeanOfAllNotesAndGenerateStudentTranscriptResponse(
            final StudentInfoResponse studentInfoResponse,
            final Map<LessonSemester, List<StudentTranscriptLessonNoteResponse>> tempStudentLessonsSemestersNotesResponse) {

        double totalCreditMultiplyNote = 0D;
        int totalCreditCount = 0;
        final Map<LessonSemester, StudentTranscriptLessonNoteSemesterResponse> studentLessonsSemestersNotesResponse = new TreeMap<>();
        for (Map.Entry<LessonSemester, List<StudentTranscriptLessonNoteResponse>> lessonsSemestersNotesResponse : tempStudentLessonsSemestersNotesResponse.entrySet()) {

            final LessonSemester lessonSemester = lessonsSemestersNotesResponse.getKey();
            final List<StudentTranscriptLessonNoteResponse> studentTranscriptLessonNoteResponses = lessonsSemestersNotesResponse.getValue();

            double semesterTotalCreditMultiplyNote = 0D;
            int semesterTotalCreditCount = 0;
            for (StudentTranscriptLessonNoteResponse studentTranscriptLessonNoteResponse : studentTranscriptLessonNoteResponses) {
                semesterTotalCreditMultiplyNote += studentTranscriptLessonNoteResponse.getCredit() * studentTranscriptLessonNoteResponse.getMeanOfNote();
                semesterTotalCreditCount += studentTranscriptLessonNoteResponse.getCredit();
            }
            totalCreditMultiplyNote += semesterTotalCreditMultiplyNote;
            totalCreditCount += semesterTotalCreditCount;

            final double meanOfSemesterAllNotes = semesterTotalCreditMultiplyNote / semesterTotalCreditCount;

            final StudentTranscriptLessonNoteSemesterResponse studentTranscriptLessonNoteSemesterResponse = StudentTranscriptLessonNoteSemesterResponse.builder()
                    .meanOfAllNotes(StudentTranscriptUtil.getMeanOfNoteWith2NumberAfterDot(meanOfSemesterAllNotes))
                    .studentTranscriptLessonNoteResponse(studentTranscriptLessonNoteResponses).build();

            studentLessonsSemestersNotesResponse.put(lessonSemester, studentTranscriptLessonNoteSemesterResponse);
        }

        final Double meanOfAllNotes = totalCreditMultiplyNote / totalCreditCount;

        return StudentTranscriptResponse.builder()
                .studentLessonsSemestersNotesResponse(studentLessonsSemestersNotesResponse)
                .meanOfAllNotes(StudentTranscriptUtil.getMeanOfNoteWith2NumberAfterDot(meanOfAllNotes))
                .studentInfoResponse(studentInfoResponse)
                .fileDownloadUrl(studentTranscriptInfoConverter.createFileDownloadUrl(studentInfoResponse.getStudentId()))
                .build();
    }


    @Override
    public void exportStudentTranscriptFileByStudentId(final Long studentId,
                                                       final HttpServletResponse httpServletResponse)
            throws SisNotExistException, SisAlreadyException, IOException, SisProcessException {

        ifStudentIsNotExistThrowNotExistException(studentId);

        final StudentTranscriptResponse studentTranscriptResponse = getStudentTranscriptDetailByStudentId(studentId);

        createAndExportDocument(studentTranscriptResponse, httpServletResponse);
    }

    private void createAndExportDocument(final StudentTranscriptResponse studentTranscriptResponse,
                                         final HttpServletResponse httpServletResponse)
            throws IOException, SisProcessException {

        final StudentInfoResponse studentInfoResponse = studentTranscriptResponse.getStudentInfoResponse();
        final Map<LessonSemester, StudentTranscriptLessonNoteSemesterResponse> studentLessonsSemestersNotesResponse = studentTranscriptResponse
                .getStudentLessonsSemestersNotesResponse();

        final Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

        document.open();

        addDocumentHeader(document);

        addSpace(document);

        addStudentInformation(document, studentInfoResponse, studentTranscriptResponse.getMeanOfAllNotes());

        addSpace(document);

        for (Map.Entry<LessonSemester, StudentTranscriptLessonNoteSemesterResponse> studentLessonsSemestersNotes : studentLessonsSemestersNotesResponse.entrySet()) {
            final LessonSemester semester = studentLessonsSemestersNotes.getKey();
            final StudentTranscriptLessonNoteSemesterResponse studentTranscriptLessonNoteSemesterResponse = studentLessonsSemestersNotes.getValue();

            addTableToDoc(document, semester, studentTranscriptLessonNoteSemesterResponse);
        }

        document.close();
    }

    private void addDocumentHeader(final Document document) throws IOException {
        final Font headerFont = fontGeneratorService.generatePhenomenaFont(Phenomena.EXTRA_BOLD, 30, SisColor.DARK_BLUE);
        document.add(pdfGeneratorService.generateParagraph("SIS TRANSKRİPT", headerFont, Paragraph.ALIGN_CENTER));
    }

    private void addStudentInformation(final Document document,
                                       final StudentInfoResponse studentInfoResponse,
                                       final Double meanOfAllNotes) throws IOException {

        addStudentInfoHeader(document, "Öğrenci Bilgileri");

        addStudentInfo(document, "Kayıt Tarihi : ", studentInfoResponse.getRegistrationDate());
        addStudentInfo(document, "Adı Soyadı : ", studentInfoResponse.getName() + " " + studentInfoResponse.getSurname());
        addStudentInfo(document, "Öğrenci Numarası : ", studentInfoResponse.getStudentId().toString());
        addStudentInfo(document, "Bölümü : ", studentInfoResponse.getDepartmentResponse().getName());
        addStudentInfo(document, "Genel Ortalama : ", meanOfAllNotes.toString());
    }

    private void addStudentInfoHeader(final Document document,
                                      final String headerText) throws IOException {

        Font headerFont = fontGeneratorService.generatePhenomenaFont(Phenomena.EXTRA_BOLD, 24, SisColor.DARK_BLUE);
        document.add(pdfGeneratorService.generateParagraph(headerText, headerFont, Paragraph.ALIGN_LEFT));
    }

    private void addStudentInfo(final Document document,
                                final String preTextBeforeInfo,
                                final String info) throws IOException {

        Font preTextInfoFont = fontGeneratorService.generatePhenomenaFont(Phenomena.BOLD, 18, SisColor.DARK_BLUE);
        Font infoFont = fontGeneratorService.generatePhenomenaFont(Phenomena.REGULAR, 18, SisColor.DARK_BLUE);

        Paragraph registrationDateParagraph = pdfGeneratorService.generateParagraph(preTextBeforeInfo, preTextInfoFont, Paragraph.ALIGN_LEFT);
        registrationDateParagraph.add(pdfGeneratorService.generateParagraph(info, infoFont, Paragraph.ALIGN_LEFT));
        document.add(registrationDateParagraph);
    }

    private void addTableToDoc(final Document document,
                               final LessonSemester semester,
                               final StudentTranscriptLessonNoteSemesterResponse studentTranscriptLessonNoteSemesterResponse)
            throws IOException, SisProcessException {

        final String meanOfSemesterNotes = studentTranscriptLessonNoteSemesterResponse.getMeanOfAllNotes().toString();
        addParagraphBeforeTable(document, semester, meanOfSemesterNotes);

        final PdfPTable table = createTableWithHeader();

        for (final StudentTranscriptLessonNoteResponse studentTranscriptLessonNoteResponse : studentTranscriptLessonNoteSemesterResponse.getStudentTranscriptLessonNoteResponse()) {
            table.addCell(generateBaseCell(studentTranscriptLessonNoteResponse.getLessonId().toString()));
            table.addCell(generateBaseCell(studentTranscriptLessonNoteResponse.getName()));
            table.addCell(generateBaseCell(studentTranscriptLessonNoteResponse.getCredit().toString()));
            table.addCell(generateBaseCell(studentTranscriptLessonNoteResponse.getCompulsoryOrElective().getTr()));
            table.addCell(generateBaseCell(studentTranscriptLessonNoteResponse.getMeanOfNote().toString()));
            table.addCell(generateCellForNoteStatus(studentTranscriptLessonNoteResponse.getStatus()));
        }

        document.add(table);
    }

    private void addParagraphBeforeTable(final Document document,
                                         final LessonSemester semester,
                                         final String meanOfSemesterNotes) throws IOException {

        final Font subHeaderFont = fontGeneratorService.generatePhenomenaFont(Phenomena.EXTRA_BOLD, 20, SisColor.BLUE);
        document.add(pdfGeneratorService.generateParagraph(semester.getTr(), subHeaderFont, Paragraph.ALIGN_LEFT));

        final Font meanOfSemesterNotesHeaderFont = fontGeneratorService.generatePhenomenaFont(Phenomena.BOLD, 16, SisColor.BLUE);
        final Paragraph semesterNotesParagraph = pdfGeneratorService.generateParagraph("Not Ortalaması : ", meanOfSemesterNotesHeaderFont, Paragraph.ALIGN_LEFT);

        final Font meanOfSemesterNotesFont = fontGeneratorService.generatePhenomenaFont(Phenomena.REGULAR, 16, SisColor.BLUE);
        semesterNotesParagraph.add(pdfGeneratorService.generateParagraph(meanOfSemesterNotes, meanOfSemesterNotesFont, Paragraph.ALIGN_LEFT));

        document.add(semesterNotesParagraph);
    }

    private PdfPTable createTableWithHeader() throws IOException {
        final PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.7f, 6f, 1.3f, 3.1f, 2.2f, 1.85f});
        table.setSpacingBefore(10);

        table.addCell(generateHeaderCell("Ders Kodu"));
        table.addCell(generateHeaderCell("Ders Adı"));
        table.addCell(generateHeaderCell("Kredi"));
        table.addCell(generateHeaderCell("Zorunlu/Seçmeli"));
        table.addCell(generateHeaderCell("Not Ortalaması"));
        table.addCell(generateHeaderCell("Not Durumu"));

        return table;
    }

    private PdfPCell generateCellForNoteStatus(final StudentLessonNoteStatus noteStatus)
            throws IOException, SisProcessException {

        final Color backgroundColor;
        switch (noteStatus) {
            case PASSED -> backgroundColor = SisColor.GREEN;
            case FAILED -> backgroundColor = SisColor.RED;
            default -> throw new SisProcessException("StudentLessonNoteStatus Not Exist!");
        }

        final Font font = fontGeneratorService.generatePhenomenaFont(Phenomena.BOLD, 14, SisColor.WHITE);
        return pdfGeneratorService.generateCell(noteStatus.getTr(), font, Color.WHITE, backgroundColor);
    }

    private PdfPCell generateHeaderCell(final String text) throws IOException {
        final Font font = fontGeneratorService.generatePhenomenaFont(Phenomena.BOLD, 16, SisColor.WHITE);
        return pdfGeneratorService.generateCell(text, font, SisColor.WHITE, SisColor.YELLOW);
    }

    private PdfPCell generateBaseCell(final String text) throws IOException {
        final Font font = fontGeneratorService.generatePhenomenaFont(Phenomena.REGULAR, 14, SisColor.DARK_BLUE);
        return pdfGeneratorService.generateCell(text, font, SisColor.WHITE, SisColor.WHITE);
    }

    private void addSpace(final Document document) throws IOException {
        final Font font = fontGeneratorService.generatePhenomenaFont(Phenomena.THIN, 30, SisColor.WHITE);
        document.add(pdfGeneratorService.generateParagraph("       ", font, Paragraph.ALIGN_CENTER));
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        studentOutService.ifStudentIsNotExistThrowNotExistException(studentId);
    }
}
