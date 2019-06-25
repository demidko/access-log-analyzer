package ru.farpost.exceptions.log;

public class TimeParseException extends RequestParseException {

    public TimeParseException(final long line) {
        super(line, "time");
    }

    public TimeParseException(final long line, final int row, final String err) {
        super(line, row, "time", err);
    }
}
