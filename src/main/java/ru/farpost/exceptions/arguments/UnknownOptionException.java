package ru.farpost.exceptions.arguments;

public class UnknownOptionException extends ArgumentException {

    public UnknownOptionException(final String option) {

        super(String.format("unknown option %s", option));
    }
}
