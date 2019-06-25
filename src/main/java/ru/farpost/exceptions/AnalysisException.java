package ru.farpost.exceptions;

public class AnalysisException extends Exception {

    public AnalysisException(final String message) {
        super("> " + message);
    }
}
