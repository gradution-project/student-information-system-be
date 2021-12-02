package com.graduationproject.studentinformationsystem.common.util;

import java.util.*;

public class SisUtil {

    private SisUtil() {
    }

    public static String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer year = calendar.get(Calendar.YEAR);
        return String.valueOf(year);
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

    public static boolean isExistIdInIdList(Long id, List<Long> ids) {
        for (Long idInList : ids) {
            if (id.equals(idInList)) {
                return true;
            }
        }
        return false;
    }
}
