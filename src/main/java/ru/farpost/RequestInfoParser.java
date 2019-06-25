package ru.farpost;

import ru.farpost.exceptions.log.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class RequestInfoParser {
    // current line
    private long lineIndex = 0;
    private int symbolIndex;
    private String line;

    // buffer for new RequestInfo
    private LocalDateTime dateTime;
    private short code;


    private RequestInfoParser setLine_1(final String s) {
        ++lineIndex;
        line = s;
        return this;
    }


    private RequestInfoParser parseTime_2() throws TimeParseException {

        final var firstIndex = line.indexOf('[') + 1;
        final var lastIndex = line.indexOf(' ', firstIndex);
        if ( firstIndex >= line.length() || firstIndex == -1
                || lastIndex == -1 || lastIndex >= line.length()) {
            throw new TimeParseException(lineIndex);
        }
        final var str = line.substring(firstIndex, lastIndex);
        try {
            symbolIndex = lastIndex + 1;
            dateTime = LocalDateTime.from(DateTimeFormatter
                    .ofPattern("d/M/u:H:m:s")
                    .parse(str));
            return this;
        } catch (final DateTimeParseException e) {
            throw new TimeParseException(lineIndex, e.getErrorIndex(), str);
        }
    }


    private RequestInfoParser parseCode_3() throws CodeParseException {

        final var firstIndex = line.indexOf(
                '"', line.indexOf('"', symbolIndex) + 1) + 2;
        final var lastIndex = line.indexOf(' ', firstIndex);
        if (firstIndex == -1 || firstIndex >= line.length()
                || lastIndex == -1 || lastIndex >= line.length()) {
            throw new CodeParseException(lineIndex);
        }
        final var str = line.substring(firstIndex, lastIndex);
        try {
            symbolIndex = lastIndex + 1;
            code = Short.parseShort(str);
            return this;
        } catch (final NumberFormatException e) {
            throw new CodeParseException(lineIndex, firstIndex, str);
        }
    }


    private RequestInfo parseTimeSpan_4() throws TimeSpanParseException {

        final var firstIndex = line.indexOf(' ', symbolIndex) + 1;
        final var lastIndex = line.indexOf(' ', firstIndex);
        if (firstIndex == -1 || firstIndex >= line.length()
                || lastIndex == -1 || lastIndex >= line.length()) {
            throw new TimeSpanParseException(lineIndex);
        }
        final var str = line.substring(firstIndex, lastIndex);
        try {
            return new RequestInfo(dateTime, code, Double.parseDouble(str));
        } catch (final NumberFormatException e) {
            throw new TimeSpanParseException(lineIndex, firstIndex, str);
        }
    }


    public RequestInfo parse(final String s) throws LogException {

        return setLine_1(s)
                .parseTime_2()
                .parseCode_3()
                .parseTimeSpan_4();
    }
}
