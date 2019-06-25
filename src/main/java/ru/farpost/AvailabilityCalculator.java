package ru.farpost;

public class AvailabilityCalculator {

    private double okTime = 0;
    private double failTime = 0;

    private static double seconds(final double milliseconds) {
        return milliseconds / 1000; // seconds
    }

    public double getAvailabilityPercent() {
        return okTime / (okTime + failTime) * 100;
    }

    public void fail(final double milliseconds) {
       failTime += seconds(milliseconds);
    }

    public void ok(final double milliseconds) {
        okTime += seconds(milliseconds);
    }
}
