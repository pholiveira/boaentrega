package br.com.boaentrega.berouteplanner.commons;

import java.time.LocalDateTime;

public class Utils {

    public static LocalDateTime getStartTime(String startTime) {
        var arrayTime = startTime.split(":");
        return LocalDateTime.now().with(java.time.LocalTime.of(Integer.parseInt(arrayTime[0]), Integer.parseInt(arrayTime[1])));
    }
}
