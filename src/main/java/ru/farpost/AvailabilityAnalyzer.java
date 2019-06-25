package ru.farpost;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


public class AvailabilityAnalyzer {

    private final Options options;
    private final AvailabilityCalculator calculator = new AvailabilityCalculator();

    private LocalDateTime criticalPercentsStart;
    private LocalDateTime criticalPercentsFinish;
    private final ArrayList<Double> criticalPercents = new ArrayList<>();


    private boolean hasData() {
        return criticalPercentsFinish != null;
    }


    private void uploadData() {

        // Так как в файле с логом кроме времени содержались еще и даты
        // Их тоже можно выводить опционально
        // Поэтому и используется соответствующий класс (LocalDateTime вместо LocalTime)
        final var timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        final var percentsFmt = new DecimalFormat("#.######", new DecimalFormatSymbols(Locale.US));
        System.out.print(timeFmt.format(criticalPercentsStart));
        System.out.print('\t');
        System.out.print(timeFmt.format(criticalPercentsFinish));
        System.out.print('\t');
        System.out.print(percentsFmt.format(
                criticalPercents.stream().mapToDouble(Double::doubleValue).average().getAsDouble()));
        System.out.println();
        
        criticalPercentsFinish = null;
        criticalPercentsStart = null;
        criticalPercents.clear();
    }


    private boolean isRequestFail(final RequestInfo info) {

        return info.timespan > options.responseTime || (info.code >= 500 && info.code < 600);
    }


    public void onRequest(final RequestInfo info) {

        if (isRequestFail(info)) {
            calculator.fail(info.timespan);
        } else {
            calculator.ok(info.timespan);
        }

        final var currentAvailabilityPercent = calculator.getAvailabilityPercent();

        if (currentAvailabilityPercent < options.availabilityPercent) {
            if (criticalPercentsStart == null) {
                criticalPercentsStart = info.time;
                criticalPercentsFinish = info.time;
            } else {
                criticalPercentsFinish = info.time;
            }
            criticalPercents.add(currentAvailabilityPercent);
            return;
        }

        onEof();
    }


    public void onEof() {
        if (hasData()) {
            uploadData();
        }
    }


    public AvailabilityAnalyzer(final Options opt) {

        options = opt;
    }
}
