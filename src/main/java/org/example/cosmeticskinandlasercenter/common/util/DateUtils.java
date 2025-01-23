package org.example.cosmeticskinandlasercenter.common.util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;
import org.example.cosmeticskinandlasercenter.common.exception.ValidationException;

@Slf4j
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }

    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
        } catch (DateTimeParseException e) {
            log.warn("Date parsing failed for input: {}", dateStr, e);
            throw new ValidationException("Invalid date format. Expected format: " + DEFAULT_DATE_FORMAT);
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            log.warn("DateTime parsing failed for input: {}", dateTimeStr, e);
            throw new ValidationException("Invalid datetime format. Expected format: " + DEFAULT_DATETIME_FORMAT);
        }
    }
}