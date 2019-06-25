package ru.farpost;

import ru.farpost.exceptions.arguments.*;

import java.util.ArrayList;
import java.util.Arrays;

public class OptionsParser {

    public static Options parse(final String[] args) throws ArgumentException {

        double uptime = -1;
        long responseTime = -1;

        for (final var it = Arrays.stream(args).iterator(); it.hasNext(); ) {

            final var option = it.next();
            if (!it.hasNext()) {
                throw new NoOptionValueException(option);
            }
            final var optionValue = it.next();

            if (option.equalsIgnoreCase("-t")) {
                responseTime = parseTime(optionValue);
                continue;
            }

            if (option.equalsIgnoreCase("-u")) {
                uptime = parseAvailabilityPercent(optionValue);
                continue;
            }

            throw new UnknownOptionException(option);
        }

        checkArguments(uptime, responseTime);

        return new Options(uptime, responseTime);
    }


    private static long parseTime(final String val) throws TOptionParseException {

        long t;
        try {
            t = Long.parseLong(val);
        } catch (final NumberFormatException e) {
            throw new TOptionParseException();
        }
        if (t <= 0) {
            throw new TOptionParseException();
        }
        return t;
    }


    private static double parseAvailabilityPercent(final String val) throws UOptionParseException {

        double u;
        try {
            u = Double.parseDouble(val);
        } catch (final NumberFormatException e) {
            throw new UOptionParseException();
        }
        if (u <= 0 || u > 100) {
            throw new UOptionParseException();
        }
        return u;
    }


    private static void checkArguments(final double u, final long t) throws MissingOptionException {

        final var missedArgs = new ArrayList<String>();
        if (u == -1) {
            missedArgs.add("-u");
        }
        if (t == -1) {
            missedArgs.add("-t");
        }
        if (!missedArgs.isEmpty()) {
            throw new MissingOptionException(missedArgs);
        }
    }
}
