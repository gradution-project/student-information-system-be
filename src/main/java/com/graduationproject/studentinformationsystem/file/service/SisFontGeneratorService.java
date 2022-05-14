package com.graduationproject.studentinformationsystem.file.service;

import com.graduationproject.studentinformationsystem.file.model.enums.Phenomena;
import com.lowagie.text.Font;

import java.awt.*;
import java.io.IOException;

public interface SisFontGeneratorService {

    Font generatePhenomenaFont(Phenomena phenomena, int size, Color color) throws IOException;
}
