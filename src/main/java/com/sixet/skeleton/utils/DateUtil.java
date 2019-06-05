package com.sixet.skeleton.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Utils class for Date operations
 */
public class DateUtil {

    private static final String GMT = "GMT";
    private static final String HOUR_MINUTE_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String HOUR_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Converts a {@link Date} to {@link LocalDate} with GMT timezone
     * @param date
     * @return {@link LocalDate}
     */
    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.of(GMT)).toLocalDate();
    }

    /**
     * Converts a {@link LocalDate} to {@link Date} with GMT timezone
     * @param date
     * @return {@link Date}
     */
    public static Date toDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.of(GMT)).toInstant());
    }

    /**
     * Converts a {@link LocalDate} to {@link Date} with system default timezone
     * @param date
     * @return {@link Date}
     */
    public static Date toDateWithSystemDefaultZone(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converts a {@link Date} and {@link String} hour to {@link LocalDateTime} with GMT timezone, and default format
     * HH:mm:ss
     * @param date
     * @param hour
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(Date date, String hour) {
       return toLocalDateTime(date, hour, HOUR_MINUTE_SECOND_FORMAT);
    }

    /**
     * Converts a {@link Date} and {@link String} hour to {@link LocalDateTime} with GMT timezone, and given format
     * @param date
     * @param hour
     * @param format
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime toLocalDateTime(Date date, String hour, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(toLocalDate(date).toString().concat(" ").concat(hour), formatter);
    }

    /**
     * Converts a string into a {@link Date}, according to format
     * @param date
     * @param pattern
     * @return {@link Date}
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a string into a {@link Date}, according to format
     * @param date
     * @param pattern
     * @return {@link Date}
     */
    public static String format(Date date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a string into a {@link Date}, according to default format
     * @param date
     * @return {@link Date}
     */
    public static Date parse(String date) {
        return parse(date, DATE_FORMAT);
    }
}
