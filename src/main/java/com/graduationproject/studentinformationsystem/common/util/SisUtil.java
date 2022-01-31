package com.graduationproject.studentinformationsystem.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.DATE_PATTERN;
import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.DATE_TIME_PATTERN;

public class SisUtil {

    private SisUtil() {
    }

    public static String getFormattedPhoneNumber(final Long phoneNumber) {
        final String strPhoneNumber = String.valueOf(phoneNumber);
        final String first = strPhoneNumber.substring(0, 3);
        final String second = strPhoneNumber.substring(3, 6);
        final String third = strPhoneNumber.substring(6, 8);
        final String fourth = strPhoneNumber.substring(8, 10);
        return "+90 (" + first + ") " + second + " " + third + " " + fourth;
    }

    public static String getFormattedDate(final Date date) {
        if (date == null) {
            return null;
        } else {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            return dateFormat.format(date);
        }
    }

    public static String getFormattedDateTime(final Date date) {
        if (date == null) {
            return null;
        } else {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
            return dateFormat.format(date);
        }
    }

    public static String getCurrentFormattedDateTime() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
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

    public static Long generateRandomIdWithPrefixId(final Long prefixId) {
        final Random random = new Random();
        String stringNumber = String.valueOf(random.nextLong(999));
        stringNumber = switch (stringNumber.length()) {
            case 1 -> "00" + stringNumber;
            case 2 -> "0" + stringNumber;
            default -> stringNumber;
        };
        return Long.parseLong(prefixId + stringNumber);
    }

    public static boolean isNotExistIdInIdList(final Long id, final List<Long> ids) {
        for (Long idInList : ids) {
            if (id.equals(idInList)) {
                return false;
            }
        }
        return true;
    }

    public static boolean integerToBoolean(final Integer integer) {
        return switch (integer) {
            case 1 -> true;
            case 0 -> false;
            default -> throw new IndexOutOfBoundsException();
        };
    }
}
