package ru.farpost.exceptions.arguments;

import ru.farpost.exceptions.arguments.ArgumentException;

public class OptionParseException extends ArgumentException {

    public OptionParseException(final String option, final String format) {
        super(String.format("option %s must be %s", option, format));
    }
}
