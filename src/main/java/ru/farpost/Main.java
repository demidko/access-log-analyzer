package ru.farpost;

import ru.farpost.exceptions.arguments.ArgumentException;
import ru.farpost.exceptions.log.LogException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static void analyze(final Options options) {
        final var analyzer = new AvailabilityAnalyzer(options);
        try (final var reader = new BufferedReader(new InputStreamReader(System.in))) {
            final var requestParser = new RequestInfoParser();
            for (var line = reader.readLine(); line != null; line = reader.readLine()) {

                try {
                    analyzer.onRequest(requestParser.parse(line));
                } catch (final LogException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (final IOException e) {
            System.out.println(e.getMessage());
        } finally {
            analyzer.onEof();
        }
    }


    public static void main(final String[] args) {

        try {
            analyze(OptionsParser.parse(args));
        } catch (final ArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
