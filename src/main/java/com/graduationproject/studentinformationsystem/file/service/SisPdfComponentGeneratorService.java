package com.graduationproject.studentinformationsystem.file.service;

import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;

import java.awt.*;

public interface SisPdfComponentGeneratorService {

    Paragraph generateParagraph(String text, Font font, int align);

    PdfPCell generateCell(String text, Font font, Color borderColor, Color backgroundColor);
}
