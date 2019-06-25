package ru.farpost.exceptions.arguments;

public class UOptionParseException extends OptionParseException {
    public UOptionParseException() {
        super("-u", "percent (0; 100], like 99.9");
    }
}
