package ru.farpost.exceptions.log;

public class RequestParseException extends LogException {

    public RequestParseException(final long line, final int row, final String what, final String from) {
        super(String.format("line %d symbol %d - unable to recognize %s from '%s'", line, row, what, from));
    }

    public RequestParseException(final long line, final String what) {
        super(String.format("line %d - unable to recognize %s", line, what));
    }
}
