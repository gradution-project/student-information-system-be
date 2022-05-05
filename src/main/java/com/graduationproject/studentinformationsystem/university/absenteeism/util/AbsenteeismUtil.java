package com.graduationproject.studentinformationsystem.university.absenteeism.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AbsenteeismUtil {

    private AbsenteeismUtil() {
    }

    public static Integer getTotalWeekBetween2Dates(final Date startDate, final Date endDate) {
        long differenceTime = endDate.getTime() - startDate.getTime();
        return (int) (TimeUnit.DAYS.convert(differenceTime, TimeUnit.MILLISECONDS) / 7);
    }

    public static Integer calculateMaxTheoreticalHours(final Integer theoreticalHours, final Integer totalWeek) {
        if (theoreticalHours == null) {
            return 0;
        }

        Double maxTheoreticalHours = (theoreticalHours * totalWeek) * 0.3;
        return Integer.parseInt(new DecimalFormat("##").format(maxTheoreticalHours));
    }

    public static Integer calculateMaxPracticeHours(final Integer practiceHours, final Integer totalWeek) {
        if (practiceHours == null) {
            return 0;
        }

        Double maxPracticeHours = (practiceHours * totalWeek) * 0.2;
        return Integer.parseInt(new DecimalFormat("##").format(maxPracticeHours));
    }
}
