package ru.farpost;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestInfo {


    public final LocalDateTime time;
    public final short code;
    public final double timespan;


    @Override
    public String toString() {

        return String.format("%s - - code %d timespan %f", DateTimeFormatter
                .ofPattern("d/M/u:H:m:s").format(time).toString(), code, timespan);
    }


    public RequestInfo(final LocalDateTime time, final short code, final double span) {

        this.code = code;
        this.timespan = span;
        this.time = time;
    }
}
