package ru.farpost.exceptions.log;

import ru.farpost.exceptions.AnalysisException;

public class LogException extends AnalysisException {

    public LogException(final String message) {
        super("Log exception: " + message);
    }
}
