package me.dinosparkour;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class Info {

    public static final String PREFIX = "/";
    public static final String AUTHOR_ID = "98457903660269568";
    static final String TOKEN = new Config().getValue("token");

    public static String getUptime() {
        long time = ManagementFactory.getRuntimeMXBean().getUptime();
        long days = TimeUnit.MILLISECONDS.toDays(time);
        time -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(time);
        time -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        time -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);

        return (days > 0 ? days + " " + (days != 1L ? "days" : "day") + " " : "") +
                (hours > 0 ? hours + " " + (hours != 1L ? "hours" : "hour") + " " : "") +
                (minutes > 0 ? minutes + " " + (minutes != 1L ? "minutes" : "minute") + " " : "") +
                (seconds > 0 ? seconds + " " + (seconds != 1L ? "seconds" : "second") : "");
    }
}