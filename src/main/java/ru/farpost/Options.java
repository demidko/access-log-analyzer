package ru.farpost;

import ru.farpost.exceptions.arguments.*;

import java.util.ArrayList;
import java.util.Arrays;

public final class Options {

    // (0%; 100%]
    public final double availabilityPercent;
    // (0ms; +infinity ms)
    public final long responseTime;


    public Options(final double u, final long t) {

        availabilityPercent = u;
        responseTime = t;
    }
}