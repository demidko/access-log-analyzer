package ru.farpost.exceptions.log;

public class CodeParseException extends RequestParseException {

    public CodeParseException(final long line) {
        super(line, "code");
    }

    public CodeParseException(final long line, final int row, final String err) {
        super(line, row, "code", err);
    }
}
