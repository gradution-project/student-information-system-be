package com.graduationproject.studentinformationsystem.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SisUtil {

    private SisUtil() {
    }

    public static String getFormattedPhoneNumber(Long phoneNumber) {
        String strPhoneNumber = String.valueOf(phoneNumber);
        String first = strPhoneNumber.substring(0, 3);
        String second = strPhoneNumber.substring(3, 6);
        String third = strPhoneNumber.substring(6, 8);
        String fourth = strPhoneNumber.substring(8, 10);
        return "+90 (" + first + ") " + second + " " + third + " " + fourth;
    }

    public static String getFormattedDate(Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    public static String getCurrentFormattedDateTime() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static String getCurrentYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }

    public static String stringToStringLowerCaseWithDot(String string) {
        string = string.toLowerCase(Locale.ROOT);

        String[] stringArray = string.split(" ");
        if (stringArray.length >= 2) {
            return string.replace(" ", ".");
        }
        return string;
    }

    public static Long generateRandomIdWithPrefixId(Long prefixId) {
        Random random = new Random();
        String stringNumber = String.valueOf(random.nextLong(999));
        stringNumber = switch (stringNumber.length()) {
            case 1 -> "00" + stringNumber;
            case 2 -> "0" + stringNumber;
            default -> stringNumber;
        };
        return Long.parseLong(prefixId + stringNumber);
    }

    public static boolean isNotExistIdInIdList(Long id, List<Long> ids) {
        for (Long idInList : ids) {
            if (id.equals(idInList)) {
                return false;
            }
        }
        return true;
    }
}
