package com.graduationproject.studentinformationsystem.common.util;

import java.util.Calendar;
import java.util.Date;

public class SisUtil {

    private SisUtil() {
    }

    public static String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer year = calendar.get(Calendar.YEAR);
        return String.valueOf(year);
    }
}
