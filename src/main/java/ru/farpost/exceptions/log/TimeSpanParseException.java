package ru.farpost.exceptions.log;

public class TimeSpanParseException extends RequestParseException {

    public TimeSpanParseException(final long line) {
        super(line, "time span");
    }

    public TimeSpanParseException(final long line, final int row, final String err) {
        super(line, row, "time span", err);
    }
}
