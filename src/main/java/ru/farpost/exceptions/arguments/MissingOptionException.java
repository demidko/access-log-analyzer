package ru.farpost.exceptions.arguments;

import ru.farpost.exceptions.arguments.ArgumentException;

import java.util.ArrayList;
import java.util.Arrays;

public class MissingOptionException extends ArgumentException {

    private static String createMessage(final String... options) {

        final var resultMessage = new StringBuilder();
        for (final var it = Arrays.stream(options).iterator(); it.hasNext(); ) {
            resultMessage.append(String.format("missing %s option", it.next()));
            if (it.hasNext()) {
                resultMessage.append(", ");
            }
        }
        return resultMessage.toString();
    }

    public MissingOptionException(final String... options) {
        super(createMessage(options));
    }

    public MissingOptionException(final ArrayList<String> options) {
        this(options.toArray(new String[]{}));
    }
}
