package com.example.aarizvi2_emotilog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
Formats a date object into a string, using SimpleDateFormat. User can format any date object to
desired pattern via formatDate.
*/
public class DateFormatter {
    private SimpleDateFormat dateFormat;

    public DateFormatter(String pattern, Locale locale) {
        this.dateFormat = new SimpleDateFormat(pattern, locale);
    }

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }
}
