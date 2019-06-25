package ru.farpost.exceptions.arguments;

public class NoOptionValueException extends ArgumentException {

    public NoOptionValueException(final String option) {
        super(String.format("option %s hasn't value.", option));
    }
}
