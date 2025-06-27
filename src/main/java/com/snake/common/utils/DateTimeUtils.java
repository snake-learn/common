package com.snake.common.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Get current date time in format yyyy/MM/dd HH:mm:ss
     *
     * @return current date time
     */
    public static String now() {
        return LocalDateTime.now().format(FORMATTER);
    }

    public static String toFormattedString(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    public static LocalDateTime toLocalDateTime(String dateTime) {
        if (isParseable(dateTime)) {
            return LocalDateTime.parse(dateTime);
        }
        if (isParseable(dateTime, FORMATTER)) {
            return LocalDateTime.parse(dateTime, FORMATTER);
        }
        throw new IllegalArgumentException("Invalid date time format");
    }

    public static boolean isParseable(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isParseable(String dateTime, DateTimeFormatter formatter) {
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
