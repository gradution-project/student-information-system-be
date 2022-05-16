package com.graduationproject.studentinformationsystem.file.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Phenomena {

    BLACK("src/main/resources/fonts/Phenomena-Black.otf"),
    EXTRA_BOLD("src/main/resources/fonts/Phenomena-ExtraBold.otf"),
    BOLD("src/main/resources/fonts/Phenomena-Bold.otf"),
    REGULAR("src/main/resources/fonts/Phenomena-Regular.otf"),
    LIGHT("src/main/resources/fonts/Phenomena-Light.otf"),
    EXTRA_LIGHT("src/main/resources/fonts/Phenomena-ExtraLight.otf"),
    THIN("src/main/resources/fonts/Phenomena-Thin.otf");

    private final String path;
}
