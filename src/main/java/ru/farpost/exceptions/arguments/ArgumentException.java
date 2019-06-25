package ru.farpost.exceptions.arguments;

import ru.farpost.exceptions.AnalysisException;

public class ArgumentException extends AnalysisException {
    public ArgumentException(final String message) {
        super("Argument exception: " + message + "\nUsage: java -jar analyze -t [t] -u [u]\n" +
                " -t          minimum response time in milliseconds, 0 < t\n" +
                " -u          minimum percentage of availability, 0 < u <= 100\n");
    }
}
