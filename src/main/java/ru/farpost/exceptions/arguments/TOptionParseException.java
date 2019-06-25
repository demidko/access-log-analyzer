package ru.farpost.exceptions.arguments;

public class TOptionParseException extends OptionParseException {

    public TOptionParseException() {
        super("-t", "positive integer, like 45");
    }
}
