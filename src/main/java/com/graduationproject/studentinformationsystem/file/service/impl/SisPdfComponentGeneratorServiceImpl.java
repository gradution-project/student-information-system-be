package com.graduationproject.studentinformationsystem.file.service.impl;

import com.graduationproject.studentinformationsystem.file.service.SisPdfComponentGeneratorService;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class SisPdfComponentGeneratorServiceImpl implements SisPdfComponentGeneratorService {

    @Override
    public Paragraph generateParagraph(String text, Font font, int align) {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(align);
        return paragraph;
    }

    @Override
    public PdfPCell generateCell(String text, Font font, Color borderColor, Color backgroundColor) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderWidth(1);
        cell.setPadding(6);
        cell.setBorderColor(borderColor);
        cell.setBackgroundColor(backgroundColor);
        cell.setPhrase(new Phrase(text, font));
        return cell;
    }
}
