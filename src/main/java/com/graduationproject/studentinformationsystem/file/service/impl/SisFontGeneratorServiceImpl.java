package com.graduationproject.studentinformationsystem.file.service.impl;

import com.graduationproject.studentinformationsystem.file.model.enums.Phenomena;
import com.graduationproject.studentinformationsystem.file.service.SisFontGeneratorService;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class SisFontGeneratorServiceImpl implements SisFontGeneratorService {

    @Override
    public Font generatePhenomenaFont(Phenomena phenomena, int size, Color color) throws IOException {
        BaseFont baseFont = BaseFont.createFont(phenomena.getPath(), "windows-1254", true);
        return new Font(baseFont, size, Font.NORMAL, color);
    }
}
